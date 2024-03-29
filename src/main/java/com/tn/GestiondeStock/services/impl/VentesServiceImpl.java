package com.tn.GestiondeStock.services.impl;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.dto.*;
import com.tn.GestiondeStock.entities.*;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.services.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.ArticleRepository;
import com.tn.GestiondeStock.repository.LigneVenteRepository;
import com.tn.GestiondeStock.repository.VentesRepository;
import com.tn.GestiondeStock.services.VentesService;
import com.tn.GestiondeStock.validator.VentesValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    private MouvementStockService mvtStkService;


    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository,
                             LigneVenteRepository ligneVenteRepository, MouvementStockService mvtStkService) {
        super();
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.mvtStkService = mvtStkService;
    }

    @Override
    public VentesDto save(VentesDto dto) {
        List<String> errors = VentesValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Ventes n'est pas validate");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        dto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (!article.isPresent()) {
                articleErrors.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() + ("n'a ete trouve dans la BBD"));
            }
        });

        if (!articleErrors.isEmpty())  {
            log.error("one or more article are not found in th DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieur article n'ont pas ete trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(dto));
        dto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVentes(savedVentes);
            ligneVenteRepository.save(ligneVente);
            updateMvtStk(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);
    }


    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID is Null" );
            return null;
        }
        return ventesRepository.findById(id)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Vente avec l'ID =" + id + "n'ete trouve dans la BBD",
                        ErrorCodes.VENTE_NOT_FOUND));
    }

 /*   @Override
    public VentesDto findByCode(String code) {
        if (!StringUtils.hasLength(code)) {
            log.error("Vente Code is Null" );
            return null;

        }		return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente client n'a ete trouve avec le Code" + code, ErrorCodes.VENTE_NOT_FOUND
                ));
    }
*/
    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return;
        }
        List<LigneVente> ligneVentes = ligneVenteRepository.findAllByVentesId   (id);
        if (!ligneVentes.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer une vente d'execute",
                    ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        ventesRepository.deleteById(id);
    }


    private void updateMvtStk(LigneVente lig) {
        MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
                .article(ArticleDto.fromEntity(lig.getArticle()))
                .dateMvt(Instant.now())
                .typemvt(TypeMvtStock.SORTIE)
                .sourceMvt(SourceMvtStk.VENTE)
                .quantite(lig.getQuantite())
                .idEntreprise(lig.getIdEntreprise())
                .build();
        mvtStkService.entreeStock(mouvementStockDto);
    }

}