package com.tn.GestiondeStock.dto;

import java.util.Date;
import java.util.List;

import com.tn.GestiondeStock.entities.CommandeFournisseur;

import com.tn.GestiondeStock.entities.EtatCommande;
import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class CommandeFournisseurDto {
	
	private Integer id;
	
	private String code;

	private Date dateCommande;

	private EtatCommande etatCommande;

	private FournisseurDto fournisseur;
		
	private Integer idEntreprise;
	
	private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;
	
	
	public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {
		if (commandeFournisseur == null) {
			return null;
			//TODO throw an exception 
		}
		

		return CommandeFournisseurDto.builder()
				.id(commandeFournisseur.getId())
				.code(commandeFournisseur.getCode())
				.dateCommande(commandeFournisseur.getDateCommande())
				.idEntreprise(commandeFournisseur.getIdEntreprise())
				.etatCommande((commandeFournisseur.getEtatCommande()))
				.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
				.build();
	}

	
	public static CommandeFournisseur toEntity(CommandeFournisseurDto dto) {
		if (dto == null) {
			return null;
			//TODO throw an exception 
		}
		
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(dto.getId());
		commandeFournisseur.setCode(dto.getCode());
		commandeFournisseur.setIdEntreprise(dto.getIdEntreprise());
		commandeFournisseur.setDateCommande(dto.getDateCommande());
		commandeFournisseur.setEtatCommande(dto.getEtatCommande());
		commandeFournisseur.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));


		return commandeFournisseur;
	}

	public boolean isCommandeLivree() { return EtatCommande.LIVREE.equals(this.etatCommande);}

}
