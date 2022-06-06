package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Article;

public interface ArticleRepository extends JpaRepository<Integer, Article> {
	
}
