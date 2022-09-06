package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.ArticleDto;
import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneVenteDto;

public interface ArticleService {

	ArticleDto save (ArticleDto dto);
	
	ArticleDto findById (Integer id);
	
	ArticleDto findByCodeArticle (String codeArticle);
	
	List<ArticleDto> findAll();

	List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

	List<LigneCommandeClientDto> findHistoriqueCommandeClient(Integer idArticle);

	List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

	List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);
	
	void delete(Integer id );
}
