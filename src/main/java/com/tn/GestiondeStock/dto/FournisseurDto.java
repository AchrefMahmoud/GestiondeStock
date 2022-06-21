package com.tn.GestiondeStock.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.entities.Fournisseur;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class FournisseurDto {
	
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
	
//	private Integer idEntreprise;
	
	private List<CommandeFournisseurDto> commandeFournisseurs;
	
	
	
	public static FournisseurDto fromEntity(Fournisseur fournisseur) {
		if (fournisseur == null) {
			return null;
			//TODO throw an exception 
		}
		

		return FournisseurDto.builder()
				.id(fournisseur.getId())
				.nom(fournisseur.getNom())
				.prenom(fournisseur.getPrenom())
				.photo(fournisseur.getPhoto())
				.mail(fournisseur.getMail())
				.numTel(fournisseur.getNumTel())
				.adresse(fournisseur.getAdresse())
				.ville(fournisseur.getVille())
				.codePostale(fournisseur.getCodePostale())
				.pays(fournisseur.getPays())
		//		.idEntreprise(fournisseur.getIdEntreprise())
				.commandeFournisseurs(
						fournisseur.getCommandeFournisseurs() != null ?
								fournisseur.getCommandeFournisseurs().stream()
								.map(CommandeFournisseurDto::fromEntity)
								.collect(Collectors.toList()) : null
								)
				.build();
	}
	
	public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
		if (fournisseurDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Fournisseur fournisseur = new Fournisseur();
		fournisseur.setId(fournisseurDto.getId());
		fournisseur.setNom(fournisseurDto.getNom());
		fournisseur.setPrenom(fournisseurDto.getPrenom());
		fournisseur.setPhoto(fournisseurDto.getPhoto());
		fournisseur.setMail(fournisseurDto.getMail());
		fournisseur.setNumTel(fournisseurDto.getNumTel());
		fournisseur.setAdresse(fournisseurDto.getAdresse());
		fournisseur.setVille(fournisseurDto.getVille());
		fournisseur.setCodePostale(fournisseurDto.getCodePostale());
	//	fournisseur.setIdEntreprise(fournisseurDto.getIdEntreprise());

		return fournisseur;
	}
}
