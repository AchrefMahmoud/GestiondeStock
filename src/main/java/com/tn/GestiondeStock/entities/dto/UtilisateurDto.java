package com.tn.GestiondeStock.entities.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class UtilisateurDto {

	private Integer id;

	private String nom;
	
	private String prenom;
	
	private String mail;
	
	private Date dateDeNaissance;
	
	private String MotDePasse;
	
	private String photo;
	
	private String adresse;
	
	private String ville;
	
	private String codePostale;
	
	private String pays;
	
	private EntrepriseDto entreprise;
	
	private List<RoleDto> roles;
}
