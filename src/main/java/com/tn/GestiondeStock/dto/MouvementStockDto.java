package com.tn.GestiondeStock.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;

import com.tn.GestiondeStock.entities.MouvementStock;
import com.tn.GestiondeStock.entities.SourceMvtStk;
import com.tn.GestiondeStock.entities.TypeMvtStock;

import lombok.Builder;
import lombok.Data;


@Builder
@Data


public class MouvementStockDto {
	
	private Integer id;

	private Instant dateMvt;
	
	private BigDecimal quantite;
	
	private ArticleDto article;
	
	private TypeMvtStock typemvt;

	private SourceMvtStk sourceMvt;
	
	private Integer idEntreprise;
	
	public static MouvementStockDto fromEntity(MouvementStock mouvementStock) {
		if (mouvementStock == null) {
			return null;
	}
	
		return MouvementStockDto.builder()
				.id(mouvementStock.getId())
				.dateMvt(mouvementStock.getDateMvt())
				.quantite(mouvementStock.getQuantite())
				.idEntreprise(mouvementStock.getIdEntreprise())
				.typemvt(mouvementStock.getTypemvt())
				.sourceMvt(mouvementStock.getSourceMvt())
				.article(ArticleDto.fromEntity(mouvementStock.getArticle()))
				.build();
	}
	
	
	public static MouvementStock toEntity(MouvementStockDto mouvementStockDto) {
		if (mouvementStockDto == null) {
			return null;
			//TODO throw an exception 
		}
		MouvementStock mouvementStock = new MouvementStock();
		mouvementStock.setId(mouvementStockDto.getId());
		mouvementStock.setDateMvt(mouvementStockDto.getDateMvt());
		mouvementStock.setQuantite(mouvementStockDto.getQuantite());
		mouvementStock.setIdEntreprise(mouvementStockDto.getIdEntreprise());
		mouvementStock.setArticle(ArticleDto.toEntity(mouvementStockDto.getArticle()));
		mouvementStock.setTypemvt(mouvementStockDto.getTypemvt());
		mouvementStock.setSourceMvt(mouvementStockDto.getSourceMvt());

		
		return mouvementStock;
	}
}
