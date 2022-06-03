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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "commandeclient")

public class CommandeClient extends AbstractEntity {

	@Column(name = "Code")
	private String code;
	
	@Column(name = "datecommande")
	private Date dateCommande;
	
	@ManyToOne
	@JoinColumn(name = "idclient")
	private Client client;
	
	@OneToMany(mappedBy = "commandeClient")
	private List<LigneCommandeClient> LigneCommandeClients;
	
}
