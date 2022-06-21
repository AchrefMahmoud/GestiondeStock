package com.tn.GestiondeStock.dto;

import java.util.Date;

import com.tn.GestiondeStock.entities.Vente;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class VenteDto {

	private Integer id;

	private String Code;
	
	private Date dateVente;
	
	private String commentaire;

//	private Integer idEntreprise;
	
	public static VenteDto fromEntity(Vente vente) {
		if (vente == null) {
			return null;
			//TODO throw an exception 
		}
		

		return VenteDto.builder()
				.id(vente.getId())
				.Code(vente.getCode())
				.dateVente(vente.getDateVente())
				.commentaire(vente.getCommentaire())
//				.idEntreprise(vente.getIdEntreprise())
				.build();
	}

	
	public Vente toEntity(VenteDto venteDto) {
		if (venteDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Vente vente = new Vente();
		vente.setId(venteDto.getId());
		vente.setCode(venteDto.getCode());
		vente.setDateVente(venteDto.getDateVente());
		vente.setCommentaire(venteDto.getCommentaire());
//		vente.setIdEntreprise(venteDto.getIdEntreprise());
		
		return vente;
	}
}
