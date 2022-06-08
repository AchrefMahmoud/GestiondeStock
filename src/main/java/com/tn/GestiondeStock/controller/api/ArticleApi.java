package com.tn.GestiondeStock.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tn.GestiondeStock.dto.ArticleDto;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;

public interface ArticleApi {


	@PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	ArticleDto save (@RequestBody ArticleDto dto);
	
	@GetMapping(value = APP_ROOT + "/articles/idArticle", produces = MediaType.APPLICATION_JSON_VALUE)
	ArticleDto findById (@PathVariable Integer id);
	
	ArticleDto findByCodeArticle (String codeArticle);
	
	List<ArticleDto> findAll();
	
	void delete(Integer id );
	
}
