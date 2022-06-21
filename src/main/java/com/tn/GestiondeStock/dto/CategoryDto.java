package com.tn.GestiondeStock.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tn.GestiondeStock.entities.Category;

import lombok.Builder;
import lombok.Data;


@Builder
@Data

public class CategoryDto {

	private Integer id;

	private String codeCategory;
	
	private String designation;
	
//	private Integer idEntreprise;
	
	@JsonIgnore
	private List<ArticleDto> articles;  
	
	
	public static CategoryDto fromEntity(Category category) {
		if (category == null) {
			return null;
			//TODO throw an exception 
		}
		
//category --> CategoryDto	
		return CategoryDto.builder()
				.id(category.getId())
				.designation(category.getDesignation())
				.codeCategory(category.getCodeCategory())
		//		.idEntreprise(category.getIdEntreprise())
				.build();
	}
	
	
//CategoryDto --> Category
		
		public static Category toEntity(CategoryDto categoryDto) {
			if (categoryDto == null) {
				return null;
				//TODO throw an exception 
			}
			
			Category category = new Category();
			category.setId(categoryDto.getId());
			category.setCodeCategory(categoryDto.getCodeCategory());
			category.setDesignation(categoryDto.getDesignation());
	//		category.setIdEntreprise(categoryDto.getIdEntreprise());
			
			return category;
			
	}
}
