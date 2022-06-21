package com.tn.GestiondeStock.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "client")

public class Client extends AbstractEntity {

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
	
	//@Column (name = "identreprise")
	//private Integer idEntreprise;
	
	@Column(name = "pays")
	private String pays;
	
	@OneToMany(mappedBy = "client")
	private List<CommandeClient> commandeClients;
}
