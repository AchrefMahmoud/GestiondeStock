package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.ArticleApi;
import com.tn.GestiondeStock.dto.ArticleDto;
import com.tn.GestiondeStock.services.ArticleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController implements ArticleApi  {

	private ArticleService articleService;
	
	@Autowired
	public ArticleController(ArticleService articleService)  {
		this.articleService = articleService; 
	}
	
	@Override
	public ArticleDto save(ArticleDto dto) {
		// TODO Auto-generated method stub
		return articleService.save(dto);
	}

	@Override
	public ArticleDto findById(Integer id) {
		// TODO Auto-generated method stub
		return articleService.findById(id);
	}

	@Override
	public ArticleDto findByCodeArticle(String codeArticle) {
		// TODO Auto-generated method stub
		return articleService.findByCodeArticle(codeArticle);
	}

	@Override
	public List<ArticleDto> findAll() {
		// TODO Auto-generated method stub
		return articleService.findAll();
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		articleService.delete(id);
	}
	
	

}
