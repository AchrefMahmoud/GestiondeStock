package com.tn.GestiondeStock.dto;

import java.util.Date;
import java.util.List;

import com.tn.GestiondeStock.entities.CommandeClient;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class CommandeClientDto {
	
	private Integer id;

	private String code;
	
	private Date dateCommande;
	
	private ClientDto client;
	
	private Integer idEntreprise;

	
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
		commandeClient.setId(commandeClient.getId());
		commandeClient.setCode(commandeClient.getCode());
		commandeClient.setIdEntreprise(commandeClient.getIdEntreprise());
		commandeClient.setDateCommande(commandeClient.getDateCommande());

		return commandeClient;
	}
}
