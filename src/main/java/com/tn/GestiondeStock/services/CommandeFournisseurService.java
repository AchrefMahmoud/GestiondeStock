package com.tn.GestiondeStock.services;

import java.math.BigDecimal;
import java.util.List;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.entities.EtatCommande;

public interface CommandeFournisseurService {
	
	CommandeFournisseurDto save (CommandeFournisseurDto dto);

	CommandeFournisseurDto updateEtatCommande (Integer idCommande, EtatCommande etatCommande);

	CommandeFournisseurDto updateQuantiteCommande (Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandeFournisseurDto updateFournisseur(Integer idCommande, Integer idFournisseur);

	CommandeFournisseurDto updateArticle (Integer idCommande, Integer idLigneCommande, Integer idArticle);

	// Delete article --> cad delete LigneCommandeFournisseur
	CommandeFournisseurDto deleteArticle (Integer idCommande, Integer idLigneCommande);

	CommandeFournisseurDto findById (Integer id);
	
	CommandeFournisseurDto findByCode (String code);
	
	List<CommandeFournisseurDto> findAll();

	//List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseur(Integer idCommande);
	
	void delete(Integer id );
	
}
