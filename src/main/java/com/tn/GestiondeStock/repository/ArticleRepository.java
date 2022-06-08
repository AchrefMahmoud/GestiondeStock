package com.tn.GestiondeStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
	
	 Optional <Article> findArticleByCodeArticle (String codeArticle);
	  
	  
}
