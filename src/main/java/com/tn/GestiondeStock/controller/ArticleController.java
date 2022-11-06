package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.ArticleApi;
import com.tn.GestiondeStock.dto.ArticleDto;
import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneVenteDto;
import com.tn.GestiondeStock.services.ArticleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ArticleController implements ArticleApi  {

	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService)  {
		this.articleService = articleService; 
	}
	
	@Override
	public ArticleDto saveArticle(ArticleDto dto) {
		// TODO Auto-generated method stub
		return articleService.save(dto);
	}

	@Override
	public ArticleDto findArticleById(Integer id) {
		// TODO Auto-generated method stub
		return articleService.findById(id);
	}

	@Override
	public ArticleDto findArticleByCodeArticle(String codeArticle) {
		// TODO Auto-generated method stub
		return articleService.findByCodeArticle(codeArticle);
	}

	@Override
	public List<ArticleDto> findAllArticle() {
		// TODO Auto-generated method stub
		return articleService.findAll();
	}

	@Override
	public List<LigneVenteDto> findHistoriqueVentesArticle(Integer idArticle) {
		return articleService.findHistoriqueVentes(idArticle);
	}

	@Override
	public List<LigneCommandeClientDto> findHistoriqueCommandeClientArticle(Integer idArticle) {
		return articleService.findHistoriqueCommandeClient(idArticle);
	}

	@Override
	public List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurArticle(Integer idArticle) {
		return articleService.findHistoriqueCommandeFournisseur(idArticle);
	}

	@Override
	public List<ArticleDto> findAllArticleByIdCategory(Integer idCategory) {
		return articleService.findAllArticleByIdCategory(idCategory);
	}

	@Override
	public void deleteArticle(Integer id) {
		// TODO Auto-generated method stub
		articleService.delete(id);


	}
	
	

}
