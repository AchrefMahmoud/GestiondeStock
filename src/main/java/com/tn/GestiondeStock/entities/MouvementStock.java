package com.tn.GestiondeStock.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "MouvementStock")


public class MouvementStock extends AbstractEntity {

	@Column(name = "datemvt")
	private Instant dateMvt;
	
	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@ManyToOne 
	@JoinColumn(name = "idarticle")
	private Article article;
	
	@Column(name = "typemvt")
	private TypeMvtStock typemvt;

	@Column(name = "sourcemvt")
	private SourceMvtStk sourceMvt;
	
	@Column (name = "identreprise")
	private Integer idEntreprise;
}