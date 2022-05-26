package com.tn.GestiondeStock.entities.dto;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class CommandeClientDto {
	
	private Integer id;

	private String code;
	
	private Date dateCommande;
	
	private ClientDto client;
	
	private List<LigneCommandeClientDto> LigneCommandeClients;
}
