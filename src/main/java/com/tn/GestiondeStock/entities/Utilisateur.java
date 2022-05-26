package com.tn.GestiondeStock.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Utilisateur")


public class Utilisateur extends AbstractEntity {

	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "datedenaissance")
	private Date dateDeNaissance;
	
	@Column(name = "motdepasse")
	private String MotDePasse;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "adresse")
	private String adresse;
	
	@Column(name = "ville")
	private String ville;
	
	@Column(name = "codePostale")
	private String codePostale;
	
	@Column(name = "pays")
	private String pays;
	
	@ManyToOne
	@JoinColumn(name = "identreprise")
	private Entreprise entreprise;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Role> roles;
}
