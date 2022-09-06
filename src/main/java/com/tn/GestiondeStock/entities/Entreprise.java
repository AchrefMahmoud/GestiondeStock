package com.tn.GestiondeStock.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true,exclude = "entreprise")
@Entity
@Table(name = "Entreprise")


public class Entreprise extends AbstractEntity {

	@Column(name = "nom")
	private String nom;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "codeFiscal")
	private String codeFiscal;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "numTel")
	private String numTel;
	
	@Column(name = "siteWeb")
	private String siteWeb;
	
	@OneToMany(mappedBy = "entreprise")
	private List<Utilisateur> utilisateurs;
}
