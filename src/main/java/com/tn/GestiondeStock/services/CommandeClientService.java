package com.tn.GestiondeStock.services;

import java.math.BigDecimal;
import java.util.List;

import com.tn.GestiondeStock.dto.CommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.entities.EtatCommande;

public interface CommandeClientService {

	CommandeClientDto save (CommandeClientDto dto);

	CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande);

	CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite);

	CommandeClientDto updateClient(Integer idCommande, Integer idClient);

	CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer idArticle);

	// Delete article --> le meme que delete ligneCommandeClient
	CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande);
	
	CommandeClientDto findById (Integer id);
	
	CommandeClientDto findByCode (String code);
	
	List<CommandeClientDto> findAll();

	//List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande);

	void delete(Integer id );
}
