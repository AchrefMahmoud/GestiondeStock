package com.tn.GestiondeStock.entities.dto;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tn.GestiondeStock.entities.Utilisateur;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class RoleDto {

	private Integer id;

	private String roleNom;
	
	private UtilisateurDto utilisateur;
}
