package com.tn.GestiondeStock.entities.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class EntrepriseDto {
	
	private Integer id;

	private String nom;
	
	private String description;
	
	private String adresse;
	
	private String codeFiscal;
	
	private String photo;
	
	private String mail;
	
	private String numTel;
	
	private String siteWeb;
	
	private List<UtilisateurDto> utilisateurs;
}
