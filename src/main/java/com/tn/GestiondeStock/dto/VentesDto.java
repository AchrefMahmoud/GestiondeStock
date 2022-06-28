package com.tn.GestiondeStock.dto;

import java.sql.Date;
import java.util.List;

import com.tn.GestiondeStock.entities.Ventes;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class VentesDto {

	private Integer id;

	private String Code;
		
	private String commentaire;
	
	private Date dateVente;

	private List<LigneVenteDto> ligneVentes;
	
	private Integer idEntreprise;
	
	public static VentesDto fromEntity(Ventes vente) {
		if (vente == null) {
			return null;
			//TODO throw an exception 
		}
		

		return VentesDto.builder()
				.id(vente.getId())
				.Code(vente.getCode())
				.commentaire(vente.getCommentaire())
				.idEntreprise(vente.getIdEntreprise())
				.build();
	}

	
	public static Ventes toEntity(VentesDto ventesDto) {
		if (ventesDto == null) {
			return null;
			//TODO throw an exception 
		}
		
		Ventes vente = new Ventes();
		vente.setId(ventesDto.getId());
		vente.setCode(ventesDto.getCode());
		vente.setCommentaire(ventesDto.getCommentaire());
		vente.setIdEntreprise(ventesDto.getIdEntreprise());
		
		return vente;
	}
}