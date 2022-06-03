package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;

import com.tn.GestiondeStock.entities.LigneCommandeFournisseur;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class LigneCommandeFournisseurDto {

	private Integer id;

	private ArticleDto article;
	
	private CommandeFournisseurDto commandeFournisseur;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	
	
	
	public static LigneCommandeFournisseurDto fromEntity(LigneCommandeFournisseur ligneCommandeFournisseur) {
		if (ligneCommandeFournisseur == null) {
			return null;
	}
	
		return LigneCommandeFournisseurDto.builder()
			.id(ligneCommandeFournisseur.getId())	
			.article(ArticleDto.fromEntity(ligneCommandeFournisseur.getArticle()))
			.commandeFournisseur(CommandeFournisseurDto.fromEntity(ligneCommandeFournisseur.getCommandeFournisseur()))
			.quantite(ligneCommandeFournisseur.getQuantite())	
			.prixUnitaire(ligneCommandeFournisseur.getPrixUnitaire())	
			.build();
	}
	
	
	
	public LigneCommandeFournisseur toEntity(LigneCommandeFournisseurDto ligneCommandeFournisseurDto) {
		if (ligneCommandeFournisseurDto == null) {
			return null;
			//TODO throw an exception 
		}
		LigneCommandeFournisseur ligneCommandeFournisseur = new LigneCommandeFournisseur();
		ligneCommandeFournisseur.setId(ligneCommandeFournisseurDto.getId());
		ligneCommandeFournisseur.setQuantite(ligneCommandeFournisseurDto.getQuantite());
		ligneCommandeFournisseur.setPrixUnitaire(ligneCommandeFournisseurDto.getPrixUnitaire());

		return ligneCommandeFournisseur;
	}
	
}
