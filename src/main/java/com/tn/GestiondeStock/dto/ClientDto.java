package com.tn.GestiondeStock.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tn.GestiondeStock.entities.Client;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class ClientDto {
	
	private Integer id;

	private String nom;
	
	private String prenom;
	
	private String photo;
	
	private String mail;
	
	private String numTel;
	
	private String adresse;
	
	private String ville;
	
	private String codePostale;
	
	private String pays;
	
	private Integer idEntreprise;
	
	@JsonIgnore
	private List<CommandeClientDto> commandeClients;
	
	
	
	public static ClientDto fromEntity(Client client) {
		if (client == null) {
			return null;
			//TODO throw an exception 
		}
		

		return ClientDto.builder()
				.id(client.getId())
				.nom(client.getNom())
				.prenom(client.getPrenom())
				.photo(client.getPhoto())
				.mail(client.getMail())
				.numTel(client.getNumTel())
				.adresse(client.getAdresse())
				.ville(client.getVille())
				.codePostale(client.getCodePostale())
				.pays(client.getPays())
				.idEntreprise(client.getIdEntreprise())
				.commandeClients(
						client.getCommandeClients() != null ?
								client.getCommandeClients().stream()
								.map(CommandeClientDto::fromEntity)
								.collect(Collectors.toList()) : null
								)
				.build();
}
	
	
	public static Client toEntity(ClientDto clientDto) {
		if (clientDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Client client = new Client();
		client.setId(clientDto.getId());
		client.setNom(clientDto.getNom());
		client.setPrenom(clientDto.getPrenom());
		client.setPhoto(clientDto.getPhoto());
		client.setMail(clientDto.getMail());
		client.setNumTel(clientDto.getNumTel());
		client.setAdresse(clientDto.getAdresse());
		client.setVille(clientDto.getVille());
		client.setCodePostale(clientDto.getCodePostale());
		client.setPays(clientDto.getPays());
		client.setIdEntreprise(clientDto.getIdEntreprise());
		
		return client;
		
}
}

