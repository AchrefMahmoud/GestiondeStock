package com.tn.GestiondeStock.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.dto.CommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.entities.Article;
import com.tn.GestiondeStock.entities.Client;
import com.tn.GestiondeStock.entities.CommandeClient;
import com.tn.GestiondeStock.entities.LigneCommandeClient;
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
	
	
	@Autowired
	public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
			ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
		super();
		this.commandeClientRepository = commandeClientRepository;
		this.ligneCommandeClientRepository = ligneCommandeClientRepository;
		this.clientRepository = clientRepository;
		this.articleRepository = articleRepository;
	}

	@Override
	public CommandeClientDto save(CommandeClientDto dto) {
			List<String> errors = CommandeClientValidator.validate(dto);
			
			if (!errors.isEmpty()) {
				log.error("Commande client n'est pas valide");
				throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
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
			
			CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(dto));
			
			if (dto.getLigneCommandeClients() != null) {
			dto.getLigneCommandeClients().forEach(LigCmdClt -> {
				LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(LigCmdClt);
				ligneCommandeClient.setCommandeClient(saveCmdClt);
				ligneCommandeClientRepository.save(ligneCommandeClient);
			} );
			}
			
		return CommandeClientDto.fromEntity(saveCmdClt);
	}

	@Override
	public CommandeClientDto findById(Integer id) {
		if (id == null ) {
			log.error("Commande client ID is Null" );
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
			log.error("Commande client Code is Null" );
			return null;
			
		}		return commandeClientRepository.findCommandeClientByCode(code)
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
		if (id == null ) {
			log.error("Commande client ID is Null" );
			return;
		}
		commandeClientRepository.deleteById(id);
	}

}
