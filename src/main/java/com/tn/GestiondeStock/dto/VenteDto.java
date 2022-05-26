package com.tn.GestiondeStock.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class VenteDto {

	private Integer id;

	private String Code;
	
	private Date dateVente;
	
	private String commentaire;
	
}
