package com.tn.GestiondeStock.services.impl;

import com.tn.GestiondeStock.dto.MouvementStockDto;
import com.tn.GestiondeStock.entities.TypeMvtStock;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.MouvementStockRepository;
import com.tn.GestiondeStock.services.ArticleService;
import com.tn.GestiondeStock.services.MouvementStockService;
import com.tn.GestiondeStock.validator.MouvementStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MouvementStockServiceImpl implements MouvementStockService {

    private MouvementStockRepository repository;
    private ArticleService articleService;

    @Autowired
    public MouvementStockServiceImpl(MouvementStockRepository repository, ArticleService articleService) {
        this.repository = repository;
        this.articleService = articleService;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
        if (idArticle == null) {
            log.warn("ID article est NULL");
            return BigDecimal.valueOf(-1);
        }
        articleService.findById(idArticle);
        return repository.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mvtStkArticle(Integer idArticle) {
        return repository.findAllByArticleId(idArticle).stream()
                .map(MouvementStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return entreePositive(dto, TypeMvtStock.ENTREE);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMvtStock.SORTIE);
    }

    @Override
    public MouvementStockDto correctionStockPos(MouvementStockDto dto) {
        return entreePositive(dto, TypeMvtStock.CORRECTION_POS);
    }

    @Override
    public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
        return sortieNegative(dto, TypeMvtStock.SORTIE);
    }


    private MouvementStockDto entreePositive(MouvementStockDto dto, TypeMvtStock typeMvtStock) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid", dto);
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide", ErrorCodes.MOUVEMENTSTOCK_NOT_VALID, errors);
        }
        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue())
                )
        );
        dto.setTypemvt(typeMvtStock);
        return MouvementStockDto.fromEntity(
                repository.save(MouvementStockDto.toEntity(dto))
        );
    }

    private MouvementStockDto sortieNegative(MouvementStockDto dto, TypeMvtStock typeMvtStock) {
        List<String> errors = MouvementStockValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Category is not valid", dto);
            throw new InvalidEntityException("Le mouvement de stock n'est pas valide", ErrorCodes.MOUVEMENTSTOCK_NOT_VALID, errors);
        }
        dto.setQuantite(
                BigDecimal.valueOf(
                        Math.abs(dto.getQuantite().doubleValue()) * -1
                )
        );
        dto.setTypemvt(typeMvtStock);
        return MouvementStockDto.fromEntity(
                repository.save(MouvementStockDto.toEntity(dto))
        );
    }
}
