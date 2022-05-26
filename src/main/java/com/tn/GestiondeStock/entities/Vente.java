package com.tn.GestiondeStock.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Vente")


public class Vente extends AbstractEntity {

	@Column(name = "code")
	private String Code;
	
	@Column(name = "datevente")
	private Date dateVente;
	
	@Column(name = "commentaire")
	private String commentaire;
}
