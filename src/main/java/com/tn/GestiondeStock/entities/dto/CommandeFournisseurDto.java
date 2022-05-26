package com.tn.GestiondeStock.entities.dto;

import java.util.Date;
import java.util.List;

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


}
