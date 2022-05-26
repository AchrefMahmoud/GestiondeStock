package com.tn.GestiondeStock.entities.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Builder
@Data

public class CategoryDto {

	private Integer id;

	private String codeCategory;
	
	private String designation;
	
	private List<ArticleDto> articles;  
}
