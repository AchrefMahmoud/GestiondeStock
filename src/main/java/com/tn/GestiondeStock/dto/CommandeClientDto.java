package com.tn.GestiondeStock.dto;

import java.util.Date;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tn.GestiondeStock.entities.CommandeClient;

import com.tn.GestiondeStock.entities.EtatCommande;
import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class CommandeClientDto {
	
	private Integer id;

	private String code;
	
	private Date dateCommande;

	private EtatCommande etatCommande;
	
	private ClientDto client;
	
	private Integer idEntreprise;

	//@JsonIgnore
	private List<LigneCommandeClientDto> LigneCommandeClients;
	
	public static CommandeClientDto fromEntity(CommandeClient commandeClient) {
		if (commandeClient == null) {
			return null;
			//TODO throw an exception 
		}
		

		return CommandeClientDto.builder()
				.id(commandeClient.getId())
				.code(commandeClient.getCode())
				.dateCommande(commandeClient.getDateCommande())
				.etatCommande((commandeClient.getEtatCommande()))
				.idEntreprise(commandeClient.getIdEntreprise())
				.client(ClientDto.fromEntity(commandeClient.getClient()))
				.build();
	}
		
		
		
	public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {
		if (commandeClientDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		CommandeClient commandeClient = new CommandeClient();
		commandeClient.setId(commandeClientDto.getId());
		commandeClient.setCode(commandeClientDto.getCode());
		commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClient()));
		commandeClient.setIdEntreprise(commandeClientDto.getIdEntreprise());
		commandeClient.setDateCommande(commandeClientDto.getDateCommande());
		commandeClient.setEtatCommande(commandeClientDto.getEtatCommande());

		return commandeClient;
	}

	public boolean isCommandeLivree() {
		return EtatCommande.LIVREE.equals(this.etatCommande);
	}
}
