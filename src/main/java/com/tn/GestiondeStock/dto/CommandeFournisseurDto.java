package com.tn.GestiondeStock.dto;

import java.util.Date;
import java.util.List;

import com.tn.GestiondeStock.entities.CommandeFournisseur;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class CommandeFournisseurDto {
	
	private Integer id;
	
	private String code;

	private Date dateCommande;

	private FournisseurDto fournisseur;
		
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
				.fournisseur(FournisseurDto.fromEntity(commandeFournisseur.getFournisseur()))
				.build();
	}

	
	public static CommandeFournisseur toEntity(CommandeFournisseurDto commandeFournisseurDto) {
		if (commandeFournisseurDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
		commandeFournisseur.setId(commandeFournisseur.getId());
		commandeFournisseur.setCode(commandeFournisseur.getCode());
		commandeFournisseur.setDateCommande(commandeFournisseur.getDateCommande());

		return commandeFournisseur;
	}

}
