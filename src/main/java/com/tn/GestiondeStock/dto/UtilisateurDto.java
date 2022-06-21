package com.tn.GestiondeStock.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.entities.Utilisateur;


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
	
//	private Integer idEntreprise;
	
	private List<RoleDto> roles;
	
	
	
	
	public static UtilisateurDto fromEntity(Utilisateur utilisateur) {
		if (utilisateur == null) {
			return null;
			//TODO throw an exception 
		}
		

		return UtilisateurDto.builder()
				.id(utilisateur.getId())
				.nom(utilisateur.getNom())
				.prenom(utilisateur.getPrenom())
				.mail(utilisateur.getMail())
				.dateDeNaissance(utilisateur.getDateDeNaissance())
				.MotDePasse(utilisateur.getMotDePasse())
				.photo(utilisateur.getPhoto())
				.adresse(utilisateur.getAdresse())
				.ville(utilisateur.getVille())
				.codePostale(utilisateur.getCodePostale())
				.pays(utilisateur.getPays())
//				.idEntreprise(utilisateur.getIdEntreprise())
				.entreprise(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
				.roles(
						utilisateur.getRoles() != null ?
								utilisateur.getRoles().stream()
								.map(RoleDto::fromEntity)
								.collect(Collectors.toList()) : null
								)
				.build();
	}
	
	
	
	public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {
		if (utilisateurDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setId(utilisateurDto.getId());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setPrenom(utilisateurDto.getPrenom());
		utilisateur.setMail(utilisateurDto.getMail());
		utilisateur.setNom(utilisateurDto.getNom());
		utilisateur.setDateDeNaissance(utilisateurDto.getDateDeNaissance());
		utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
		utilisateur.setPhoto(utilisateurDto.getPhoto());
		utilisateur.setAdresse(utilisateurDto.getAdresse());
		utilisateur.setVille(utilisateurDto.getVille());
		utilisateur.setCodePostale(utilisateurDto.getCodePostale());
		utilisateur.setPays(utilisateurDto.getPays());
//		utilisateur.setIdEntreprise(utilisateurDto.getIdEntreprise());
		
		return utilisateur;
		
}
}
