package com.tn.GestiondeStock.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.dto.*;
import com.tn.GestiondeStock.entities.*;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.services.MouvementStockService;
import com.tn.GestiondeStock.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

	private MouvementStockService mvtStkService;
	
	
	@Autowired
	public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFournisseurRepository,
			FournisseurRepository fournisseurRepository, ArticleRepository articleRepository, LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, MouvementStockService mvtStkService) {
		super();
		this.commandeFournisseurRepository = commandeFournisseurRepository;
		this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
		this.fournisseurRepository = fournisseurRepository;
		this.articleRepository = articleRepository;
		this.mvtStkService = mvtStkService;
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
		List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(id);
		if (!ligneCommandeFournisseurs.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une commande fournisseur deja utiliser",
					ErrorCodes.COMMANDE_FOURNISSEUR_ALREADY_IN_USE);
		}
		commandeFournisseurRepository.deleteById(id);
	}


	@Override
	public CommandeFournisseurDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		checkIdCommande(idCommande);
		if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
			log.error(("L'etat de la commande fournisseur est NULL"));
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat NULL",
				ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
		}
		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
		commandeFournisseur.setEtatCommande(etatCommande);
		CommandeFournisseur savedCommande = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur));
		if (commandeFournisseur.isCommandeLivree()) {
			updateMvtStk(idCommande);
		}
		return CommandeFournisseurDto.fromEntity(savedCommande);
	}



	@Override
	public CommandeFournisseurDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);

		if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
			log.error("L'ID de la ligne commande est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

		LigneCommandeFournisseur ligneCommandeFournisseur = ligneCommandeFournisseurOptional.get();
		ligneCommandeFournisseur.setQuantite(quantite);
		ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);

		return commandeFournisseur;
		}



	@Override
	public CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur) {
		checkIdCommande(idCommande);
		if (idFournisseur == null) {
			log.error("L'ID du fournisseur est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
					ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
		}

		CommandeFournisseurDto commandeFournisseur =  checkEtatCommande(idCommande);
		Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
		if (!fournisseurOptional.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune fournisseur n'a ete trouve avec l'ID" + idFournisseur, ErrorCodes.FOURNISSEUR_NOT_FOUND);
		}

		commandeFournisseur.setFournisseur(FournisseurDto.fromEntity(fournisseurOptional.get()));

		return CommandeFournisseurDto.fromEntity(
				commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseur))
		);
	}

	@Override
	public CommandeFournisseurDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);
		checkIdArticle(idArticle, "nouvel");

		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

		Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);

		Optional<Article> articleOptional = articleRepository.findById(idArticle);
		if (!articleOptional.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune article n'a ete trouve avec l'ID" + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
		}

		List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
		}

		LigneCommandeFournisseur ligneCommandeFournisseurToSaved = ligneCommandeFournisseur.get();
		ligneCommandeFournisseurToSaved.setArticle(articleOptional.get());
		ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSaved);

		return commandeFournisseur;
	}


	@Override
	public CommandeFournisseurDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);

		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
		// juste pour checker la ligneCommandeFournisseur et informe le user en cas d'absence d'article
		findLigneCommandeFournisseur(idLigneCommande);
		ligneCommandeFournisseurRepository.deleteById(idLigneCommande);
		return commandeFournisseur;
	}

/*	@Override
	public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseur(Integer idCommande) {
		return LigneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream()
				.map(LigneCommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	} */



	private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(Integer idLigneCommande) {
		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFournisseurRepository.findById(idLigneCommande);
		if (!ligneCommandeFournisseurOptional.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune ligne commande fournisseur n'a ete trouve avec l'ID" + idLigneCommande, ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND);
		}
		return ligneCommandeFournisseurOptional;
	}

	// Les CHEKS:

	private void checkIdCommande(Integer idCommande) {
		if (idCommande == null) {
			log.error("Commande client ID est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
					ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
		}
	}


	private CommandeFournisseurDto checkEtatCommande(Integer idCommande) {
		CommandeFournisseurDto commandeFournisseur = findById(idCommande);
		if (commandeFournisseur.isCommandeLivree()) {
			throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrer",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}
		return commandeFournisseur;
	}


	private void checkIdLigneCommande(Integer idLigneCommande) {
		if (idLigneCommande == null) {
			log.error("L'ID de la ligne commande est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
					ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
		}
	}

	private void checkIdArticle(Integer idArticle, String msg) {
		if (idArticle == null) {
			log.error("L'ID de " + msg + " is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un" + msg + "ID article null",
					ErrorCodes.COMMANDE_FOURNISSEUR_NON_MODIFIABLE);
		}
	}


	private void updateMvtStk(Integer idCommande) {
		List<LigneCommandeFournisseur> ligneCommandeFournisseurs = ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande);
		ligneCommandeFournisseurs.forEach(lig -> {
			MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typemvt(TypeMvtStock.ENTREE)
					.sourceMvt(SourceMvtStk.COMMANDE_FOURNISSEUR)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.entreeStock(mouvementStockDto);
		});
	}
}
