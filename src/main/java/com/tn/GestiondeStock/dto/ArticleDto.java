package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;


import com.tn.GestiondeStock.entities.Article;



import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class ArticleDto {
	
	private Integer id;

	private String codeArticle;
	
	private String designation;
	
	private BigDecimal prixUnitaireHt;
	
	private BigDecimal tauxTVA;
	
	private BigDecimal prixUnitaireTtc;
	
	private String photo;
	
	private CategoryDto category;

	
	public static ArticleDto fromEntity(Article article) {
		if (article == null) {
			return null;
	}
	
		return ArticleDto.builder()
				.id(article.getId())
				.codeArticle(article.getCodeArticle())
				.designation(article.getDesignation())
				.photo(article.getPhoto())
				.prixUnitaireTtc(article.getPrixUnitaireTtc())
				.prixUnitaireHt(article.getPrixUnitaireHt())
				.tauxTVA(article.getTauxTVA())
				.category(CategoryDto.fromEntity(article.getCategory()))
				.build();
	}
	
	
	public static Article toEntity(ArticleDto articleDto) {
		if (articleDto == null) {
			return null;
			//TODO throw an exception 
		}
		Article article = new Article();
		article.setId(articleDto.getId());
		article.setCodeArticle(articleDto.getCodeArticle());
		article.setDesignation(articleDto.getDesignation());
		article.setPhoto(articleDto.getPhoto());
		article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
		article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
		article.setTauxTVA(articleDto.getTauxTVA());
		
		return article;
	}
}