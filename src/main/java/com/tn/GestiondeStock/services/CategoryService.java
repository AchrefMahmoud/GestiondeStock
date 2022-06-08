package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.CategoryDto;

public interface CategoryService {

	CategoryDto save(CategoryDto dto);
	
	CategoryDto findById(Integer id);
	
	CategoryDto findByCodeCategory(String codeCategory);
	
	List<CategoryDto> findAll();
	
	void delete(Integer id );
	
}
