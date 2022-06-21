package com.tn.GestiondeStock.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.entities.Entreprise;


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

	public static EntrepriseDto fromEntity(Entreprise entreprise) {
		if (entreprise == null) {
			return null;
	}
	
	
	return EntrepriseDto.builder()
			.id(entreprise.getId())
			.nom(entreprise.getNom())
			.description(entreprise.getDescription())
			.mail(entreprise.getMail())
			.codeFiscal(entreprise.getCodeFiscal())
			.photo(entreprise.getPhoto())
			.siteWeb(entreprise.getSiteWeb())
			.adresse(entreprise.getAdresse())
			.utilisateurs(
					entreprise.getUtilisateurs() != null ?
							entreprise.getUtilisateurs().stream()
							.map(UtilisateurDto::fromEntity)
							.collect(Collectors.toList()) : null
							)			
			.build();
}

	public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
		if (entrepriseDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Entreprise entreprise = new Entreprise();
		entreprise.setId(entrepriseDto.getId());
		entreprise.setNom(entrepriseDto.getNom());
		entreprise.setDescription(entrepriseDto.getDescription());
		entreprise.setMail(entrepriseDto.getMail());
		entreprise.setCodeFiscal(entrepriseDto.getCodeFiscal());
		entreprise.setPhoto(entrepriseDto.getPhoto());
		entreprise.setSiteWeb(entrepriseDto.getSiteWeb());
		entreprise.setAdresse(entrepriseDto.getAdresse());

		return entreprise;
	}
}
