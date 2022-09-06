package com.tn.GestiondeStock.entities;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true,exclude = "entreprise")
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
	private Instant dateDeNaissance;
	
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
	
	@OneToMany(mappedBy = "utilisateur", fetch = FetchType.EAGER)
	private List<Role> roles;
}
