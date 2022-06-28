package com.tn.GestiondeStock.entities;


import java.util.Date;
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
@Table(name = "Vente")


public class Ventes extends AbstractEntity {

	@Column(name = "code")
	private String Code;
	
	@Column(name = "dateVente")
	private Date dateVente;
	
	@Column(name = "commentaire")
	private String commentaire;
	
	@Column (name = "identreprise")
	private Integer idEntreprise;
	
	@OneToMany(mappedBy = "ventes")
	private List<LigneVente> ligneVentes;
}
