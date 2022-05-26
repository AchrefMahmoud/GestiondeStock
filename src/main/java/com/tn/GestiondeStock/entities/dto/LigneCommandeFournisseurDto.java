package com.tn.GestiondeStock.entities.dto;

import java.math.BigDecimal;

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
	
}
