package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.entities.CommandeClient;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.repository.CommandeClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.GestiondeStock.dto.ClientDto;
import com.tn.GestiondeStock.entities.Client;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.ClientRepository;
import com.tn.GestiondeStock.services.ClientService;
import com.tn.GestiondeStock.validator.ClientValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

	private ClientRepository clientRepository;
	private CommandeClientRepository commandeClientRepository;

	@Autowired
	public ClientServiceImpl(ClientRepository clientRepository, CommandeClientRepository commandeClientRepository) {
		this.clientRepository = clientRepository;
		this.commandeClientRepository = commandeClientRepository;
	}

	@Override
	public ClientDto save(ClientDto dto) {
		List<String> errors = ClientValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Client is not valid", dto);
			throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
		}
		return ClientDto.fromEntity(clientRepository.save(ClientDto.toEntity(dto)));
	}
	
	
	@Override
	public ClientDto findById(Integer id) {
		if (id == null) {
			log.error("Client ID is null");
			return null;
		}
		
		Optional<Client> client = clientRepository.findById(id); 
				
		return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun client avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.CLIENT_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<ClientDto> findAll() {
		return clientRepository.findAll().stream()
		 .map(ClientDto::fromEntity)
		 .collect(Collectors.toList());
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Client ID is null");
			return;
		}
		List<CommandeClient> commandeClients = commandeClientRepository.findAllByClientId(id);
		if (!commandeClients.isEmpty()) {
			throw new InvalidOperationException("impossible de supprimer un client deja utiliser dans des commande",
					ErrorCodes.CLIENT_ALREADY_IN_USE);
		}
		clientRepository.deleteById(id);
	}

}
