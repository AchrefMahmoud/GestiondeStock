package com.tn.GestiondeStock.entities.dto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tn.GestiondeStock.entities.Category;

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

}
