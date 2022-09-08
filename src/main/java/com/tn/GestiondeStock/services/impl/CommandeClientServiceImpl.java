package com.tn.GestiondeStock.services.impl;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tn.GestiondeStock.dto.*;
import com.tn.GestiondeStock.entities.*;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.services.MouvementStockService;
import com.tn.GestiondeStock.validator.ArticleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.ArticleRepository;
import com.tn.GestiondeStock.repository.ClientRepository;
import com.tn.GestiondeStock.repository.CommandeClientRepository;
import com.tn.GestiondeStock.repository.LigneCommandeClientRepository;
import com.tn.GestiondeStock.services.CommandeClientService;
import com.tn.GestiondeStock.validator.CommandeClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

	private CommandeClientRepository commandeClientRepository;
	private LigneCommandeClientRepository ligneCommandeClientRepository;
	private ClientRepository clientRepository;
	private ArticleRepository articleRepository;
	private MouvementStockService mvtStkService;


	@Autowired
	public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
									 ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository, MouvementStockService mvtStkService) {
		super();
		this.commandeClientRepository = commandeClientRepository;
		this.ligneCommandeClientRepository = ligneCommandeClientRepository;
		this.clientRepository = clientRepository;
		this.articleRepository = articleRepository;
		this.mvtStkService = mvtStkService;
	}

	@Override
	public CommandeClientDto save(CommandeClientDto dto) {
		List<String> errors = CommandeClientValidator.validate(dto);

		if (!errors.isEmpty()) {
			log.error("Commande client n'est pas valide");
			throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
		}

		if (dto.getId() != null && dto.isCommandeLivree()) {
			throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrer",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}

		Optional<Client> client = clientRepository.findById(dto.getClient().getId());
		if (!client.isPresent()) {
			log.warn("Client with ID {} was not found in the BDD", dto.getClient().getId());
			throw new EntityNotFoundException("Aucun client avec l'Id" + dto.getClient().getId() + "n'est pas trouve dans la BDD",
					ErrorCodes.CLIENT_NOT_FOUND);
		}

		// on a verifier que le client existe dans la BDD, puis on va vertifier que les articles existe aussi dans la BDD

		List<String> articleErrors = new ArrayList<>();

		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach(LigCmdClt -> {
				if (LigCmdClt.getArticle() != null) {
					Optional<Article> article = articleRepository.findById(LigCmdClt.getArticle().getId());
					if (!article.isPresent()) { // !article.isPresent() for java 8, article.isEmpty() for java 11
						articleErrors.add("L'article avec l'ID" + LigCmdClt.getArticle().getId() + "n'esxiste pas");
					}
				} else {
					articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
				}
			});
		}

		if (!articleErrors.isEmpty()) {
			log.warn("");
			throw new InvalidEntityException("L'article n'existe oas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
		}

		CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));

		if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach(LigCmdClt -> {
				LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(LigCmdClt);
				ligneCommandeClient.setCommandeClient(saveCmdClt);
				ligneCommandeClientRepository.save(ligneCommandeClient);
			});
		}

		return CommandeClientDto.fromEntity(saveCmdClt);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null) {
			log.error("Commande client ID is Null");
			return null;
		}

		return commandeClientRepository.findById(id)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune commande client n'a ete trouve avec l'ID" + id, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
				));
	}

	@Override
	public CommandeClientDto findByCode(String code) {
		if (!StringUtils.hasLength(code)) {
			log.error("Commande client Code is Null");
			return null;

		}
		return commandeClientRepository.findCommandeClientByCode(code)
				.map(CommandeClientDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucune commande client n'a ete trouve avec le Code" + code, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
				));
	}

	@Override
	public List<CommandeClientDto> findAll() {
		return commandeClientRepository.findAll().stream()
				.map(CommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Commande client ID is Null");
			return;
		}
		commandeClientRepository.deleteById(id);
	}

	@Override
	public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
		checkIdCommande(idCommande);
		if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
			log.error("L'etat de la ommande client ID est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un etat null",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}

		CommandeClientDto commandeClient = checketatCommande(idCommande);
		commandeClient.setEtatCommande(etatCommande);

		CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient));
		if (commandeClient.isCommandeLivree()) {
			updateMvtStk(idCommande);
		}
		return CommandeClientDto.fromEntity(savedCmdClt);
	}

	@Override
	public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);

		if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
			log.error("L'ID de la ligne commande est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}

		CommandeClientDto commandeClient = checketatCommande(idCommande);

		Optional<LigneCommandeClient> ligneCommandeClientOptinal = findLigneCommandeClient(idLigneCommande);

		LigneCommandeClient ligneCommandeClient = ligneCommandeClientOptinal.get();
		ligneCommandeClient.setQuantite(quantite);
		ligneCommandeClientRepository.save(ligneCommandeClient);

		return commandeClient;
	}


	@Override
	public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
		checkIdCommande(idCommande);
		if (idClient == null) {
			log.error("L'ID du client est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID client null",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}

		CommandeClientDto commandeClient =  checketatCommande(idCommande);
		Optional<Client> clientOptional = clientRepository.findById(idClient);
		if (!clientOptional.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune client n'a ete trouve avec l'ID" + idClient, ErrorCodes.CLIENT_NOT_FOUND);
		}

		commandeClient.setClient(ClientDto.fromEntity(clientOptional.get()));

		return CommandeClientDto.fromEntity(
				commandeClientRepository.save(CommandeClientDto.toEntity(commandeClient))
		);
	}


	@Override
	public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);
		checkIdArticle(idArticle, "nouvel");

		CommandeClientDto commandeClient = checketatCommande(idCommande);

		Optional<LigneCommandeClient> ligneCommandeClient = findLigneCommandeClient(idLigneCommande);

		Optional<Article> articleOptional = articleRepository.findById(idArticle);
		if (!articleOptional.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune article n'a ete trouve avec l'ID" + idArticle, ErrorCodes.ARTICLE_NOT_FOUND);
		}

		List<String> errors = ArticleValidator.validate(ArticleDto.fromEntity(articleOptional.get()));
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Article invalid", ErrorCodes.ARTICLE_NOT_VALID, errors);
		}

		LigneCommandeClient ligneCommandeClientToSaved = ligneCommandeClient.get();
		ligneCommandeClientToSaved.setArticle(articleOptional.get());
		ligneCommandeClientRepository.save(ligneCommandeClientToSaved);

		return commandeClient;

	}


	@Override
	public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);

		CommandeClientDto commandeClient = checketatCommande(idCommande);
		// juste pour checker la ligneCommandeClient et informe le user en cas d'absence d'article
		findLigneCommandeClient(idLigneCommande);
		ligneCommandeClientRepository.deleteById(idLigneCommande);
		return commandeClient;
	}

	/*@Override
	public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
		return LigneCommandeClientRepository.findAllByCommandeClientId(idCommande).stream()
				.map(LigneCommandeClientDto::fromEntity)
				.collect(Collectors.toList());
	}*/

	// l'etat de la commande si elle est en preparation,valider ou bien livrer
	private void checkIdCommande(Integer idCommande) {
		if (idCommande == null) {
			log.error("Commande client ID est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un ID null",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}
	}


	private CommandeClientDto checketatCommande(Integer idCommande) {
		CommandeClientDto commandeClient = findById(idCommande);
		if (commandeClient.isCommandeLivree()) {
			throw new InvalidOperationException("Impossible de modifier la commande lorsqu'elle est livrer",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}
		return commandeClient;
	}

	private Optional<LigneCommandeClient> findLigneCommandeClient(Integer idLigneCommande) {
		Optional<LigneCommandeClient> ligneCommandeClientOptinal = ligneCommandeClientRepository.findById(idLigneCommande);
		if (!ligneCommandeClientOptinal.isPresent()) {
			throw new EntityNotFoundException(
					"Aucune ligne client n'a ete trouve avec l'ID" + idLigneCommande, ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
		}
		return ligneCommandeClientOptinal;
	}

	private void checkIdLigneCommande(Integer idLigneCommande) {
		if (idLigneCommande == null) {
			log.error("L'ID de la ligne commande est NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec une ligne de commande null",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}
	}

	private void checkIdArticle(Integer idArticle, String msg) {
		if (idArticle == null) {
			log.error("L'ID de " + msg + " is NULL");
			throw new InvalidOperationException("Impossible de modifier l'etat de la commande avec un" + msg + "ID article null",
					ErrorCodes.COMMANDE_CLIENT_NON_MODIFIABLE);
		}
	}

	private void updateMvtStk(Integer idCommande) {
		List<LigneCommandeClient> ligneCommandeClients = ligneCommandeClientRepository.findAllByCommandeClientId(idCommande);
		ligneCommandeClients.forEach(lig -> {
			MouvementStockDto mouvementStockDto = MouvementStockDto.builder()
					.article(ArticleDto.fromEntity(lig.getArticle()))
					.dateMvt(Instant.now())
					.typemvt(TypeMvtStock.SORTIE)
					.sourceMvt(SourceMvtStk.COMMANDE_CLIENT)
					.quantite(lig.getQuantite())
					.idEntreprise(lig.getIdEntreprise())
					.build();
			mvtStkService.sortieStock(mouvementStockDto);
		});
	}
}