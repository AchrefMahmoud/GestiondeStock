package com.tn.GestiondeStock.entities.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;


@Builder
@Data

public class LigneVenteDto {

	private Integer id;

	private VenteDto vente;
	
	private BigDecimal quantite;
	
	private BigDecimal prixUnitaire;
}
