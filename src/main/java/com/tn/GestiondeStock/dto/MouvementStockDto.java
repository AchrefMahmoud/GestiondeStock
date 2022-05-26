package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tn.GestiondeStock.entities.Article;
import com.tn.GestiondeStock.entities.TypeMvtStock;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class MouvementStockDto {
	
	private Integer id;

	private Date dateMvt;
	
	private BigDecimal quantite;
	
	private ArticleDto article;
	
	private TypeMvtStock typemvt;
	
}
