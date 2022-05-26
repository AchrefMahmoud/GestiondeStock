package com.tn.GestiondeStock.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fournisseur")


public class Fournisseur extends AbstractEntity {
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "numTel")
	private String numTel;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "ville")
	private String ville;
	
	@Column(name = "codePostale")
	private String codePostale;
	
	@Column(name = "pays")
	private String pays;
	
	@OneToMany(mappedBy = "fournisseur")
	private List<CommandeFournisseur> commandeFournisseurs;

}