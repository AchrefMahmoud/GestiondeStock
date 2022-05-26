package com.tn.GestiondeStock.dto;

import java.util.List;

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
	
	private List<CommandeClientDto> commandeClients;
}
