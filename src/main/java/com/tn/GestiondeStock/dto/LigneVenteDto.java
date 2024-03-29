package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;

import com.tn.GestiondeStock.entities.LigneVente;

import lombok.Builder;
import lombok.Data;


@Builder
@Data

public class LigneVenteDto {

	private Integer id;

	private VentesDto vente;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
	
	private ArticleDto article;
	
	private Integer idEntreprise;
	
	public static LigneVenteDto fromEntity(LigneVente ligneVente) {
		if (ligneVente == null) {
			return null;
	}
	
		return LigneVenteDto.builder()
			.id(ligneVente.getId())	
			.quantite(ligneVente.getQuantite())
			.prixUnitaire(ligneVente.getPrixUnitaire())
			.idEntreprise(ligneVente.getIdEntreprise())
			.article(ArticleDto.fromEntity(ligneVente.getArticle()))
			.vente(VentesDto.fromEntity(ligneVente.getVentes()))
			.build();
	}
	
	
	
	public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
		if (ligneVenteDto == null) {
			return null;
			//TODO throw an exception 
		}
		LigneVente ligneVente = new LigneVente();
		ligneVente.setId(ligneVenteDto.getId());
		ligneVente.setQuantite(ligneVenteDto.getQuantite());
		ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
		ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());

		return ligneVente;
	}
	
}
