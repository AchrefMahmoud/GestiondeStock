package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;

import com.tn.GestiondeStock.entities.LigneCommandeClient;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class LigneCommandeClientDto {

	private Integer id;

	private ArticleDto article;
	
	private CommandeClientDto commandeClient;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	
	public static LigneCommandeClientDto fromEntity(LigneCommandeClient ligneCommandeClient) {
		if (ligneCommandeClient == null) {
			return null;
	}
	
		return LigneCommandeClientDto.builder()
			.id(ligneCommandeClient.getId())	
			.article(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
			.commandeClient(CommandeClientDto.fromEntity(ligneCommandeClient.getCommandeClient()))
			.quantite(ligneCommandeClient.getQuantite())	
			.prixUnitaire(ligneCommandeClient.getPrixUnitaire())	

			.build();
	}
	
	
	public LigneCommandeClient toEntity(LigneCommandeClientDto ligneCommandeClientDto) {
		if (ligneCommandeClientDto == null) {
			return null;
			//TODO throw an exception 
		}
		LigneCommandeClient ligneCommandeClient = new LigneCommandeClient();
		ligneCommandeClient.setId(ligneCommandeClientDto.getId());
		ligneCommandeClient.setQuantite(ligneCommandeClientDto.getQuantite());
		ligneCommandeClient.setPrixUnitaire(ligneCommandeClientDto.getPrixUnitaire());

		return ligneCommandeClient;
	}
}
