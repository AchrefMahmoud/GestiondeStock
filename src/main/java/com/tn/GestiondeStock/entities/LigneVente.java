package com.tn.GestiondeStock.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "LigneVente")

public class LigneVente extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "idventes")
	private Ventes ventes;
	
	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@Column(name = "prixunitaire")
	private BigDecimal prixUnitaire;
	
	@Column (name = "identreprise")
	private Integer idEntreprise;

	@ManyToOne
	@JoinColumn(name = "idarticle")
	private Article article;

	@OneToMany(mappedBy = "ventes")
	private List<LigneVente> ligneVentes;

}