package com.tn.GestiondeStock.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.entities.Article;
import com.tn.GestiondeStock.entities.CommandeFournisseur;
import com.tn.GestiondeStock.entities.Fournisseur;
import com.tn.GestiondeStock.entities.LigneCommandeFournisseur;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.ArticleRepository;
import com.tn.GestiondeStock.repository.CommandeFournisseurRepository;
import com.tn.GestiondeStock.repository.FournisseurRepository;
import com.tn.GestiondeStock.repository.LigneCommandeFournisseurRepository;
import com.tn.GestiondeStock.services.CommandeFournisseurService;
import com.tn.GestiondeStock.validator.CommandeFournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

	private CommandeFournisseurRepository commandeFournisseurRepository;
	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
	private FournisseurRepository fournisseurRepository;
	private ArticleRepository articleRepository;
	
	
	@Autowired
	public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
			FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository) {
		super();
		this.commandeFournisseurRepository = commandeFournisseurRepository;
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public CommandeFournisseurDto save (CommandeFournisseurDto dto) {
			List<String> errors = CommandeFournisseurValidator.validate(dto);
			
			if (!errors.isEmpty()) {
				log.error("Commande fournisseur n'est pas valide");
				throw new InvalidEntityException("La commande fournisseur n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
			}
			
			Optional<Fournisseur> fournisseur = fournisseurRepository.findById(dto.getFournisseur().getId());
			if (!fournisseur.isPresent()) {
				log.warn("Fournisseur with ID {} was not found in the BDD", dto.getFournisseur().getId());
				throw new EntityNotFoundException("Aucun fournisseur avec l'Id" + dto.getFournisseur().getId() + "n'est pas trouve dans la BDD", 
						ErrorCodes.FOURNISSEUR_NOT_FOUND);
			}
			
			// on a verifier que le fournisseur existe dans la BDD, puis on va vertifier que les articles existe aussi dans la BDD
			
			List<String> articleErrors = new ArrayList<>();
			
			if (dto.getLigneCommandeFournisseurs() != null) {
				dto.getLigneCommandeFournisseurs().forEach(LigCmdFrs -> {
					if (LigCmdFrs.getArticle() != null) {
						Optional<Article> article = articleRepository.findById(LigCmdFrs.getArticle().getId());	
						if (!article.isPresent()) { // !article.isPresent() for java 8, article.isEmpty() for java 11
							articleErrors.add("L'article avec l'ID" + LigCmdFrs.getArticle().getId() + "n'esxiste pas");
						}
					}
					else {
						articleErrors.add("Impossible d'enregistrer une commande avec un article NULL"); 
					}
				} );
			}
			
			if (!articleErrors.isEmpty()) { 
				log.warn ("");
				throw new InvalidEntityException("L'article n'existe oas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
			}
			
			CommandeFournisseur saveCmdFrs = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(dto));
			
			if (dto.getLigneCommandeFournisseurs() != null) {
			dto.getLigneCommandeFournisseurs().forEach(LigCmdFrs -> {
				LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(LigCmdFrs);
				ligneCommandeFournisseur.setCommandeFournisseur(saveCmdFrs);
				ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
			} );
			}
			
		return CommandeFournisseurDto.fromEntity(saveCmdFrs);
	}

	@Override
	public CommandeFournisseurDto findById(Integer id) {
		if (id == null ) {
			log.error("Commande fournisseur ID is Null" );
			return null;
		}
		
		return commandeFournisseurRepository.findById(id)
				.map(CommandeFournisseurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune commande fournisseur n'a ete trouve avec l'ID" + id, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
						));
	}

	@Override
	public CommandeFournisseurDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("Commande fournisseur Code is Null" );
			return null;
			
		}		return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
				.map(CommandeFournisseurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune commande fournisseur n'a ete trouve avec le Code" + code, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
						));
	}

	@Override
	public List<CommandeFournisseurDto> findAll() {
		return commandeFournisseurRepository.findAll().stream()
				.map(CommandeFournisseurDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null ) {
			log.error("Commande fournisseur ID is Null" );
			return;
		}
		commandeFournisseurRepository.deleteById(id);
	}

}
