package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.CategoryDto;
import com.tn.GestiondeStock.entities.Category;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.CategoryRepository;
import com.tn.GestiondeStock.services.CategoryService;
import com.tn.GestiondeStock.validator.CategoryValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public CategoryDto save(CategoryDto dto) {
		List<String> errors = CategoryValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Category is not valid", dto);
			throw new InvalidEntityException("Le category n'est pas valide", ErrorCodes.CATEGORY_NOT_VALID, errors);
		}
		return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(dto)));
	}
	
	
	@Override
	public CategoryDto findById(Integer id) {
		if (id == null) {
			log.error("Category ID is null");
			return null;
		}
		
		Optional<Category> category = categoryRepository.findById(id); 
		
		return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun article avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.CATEGORY_NOT_FOUND)
		); 
	}
	
	
	@Override
	public CategoryDto findByCodeCategory(String codeCategory) {
		if (!StringUtils.hasLength(codeCategory)) {
			log.error("Category CODE is null");
			return null;
		}
		Optional<Category> category = categoryRepository.findCategoryByCodeCategory(codeCategory); 
		
		return Optional.of(CategoryDto.fromEntity(category.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun category avec le CODE =" + codeCategory + "n'ete trouve dans la BBD", 
				ErrorCodes.CATEGORY_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll().stream()
		 .map(CategoryDto::fromEntity)
		 .collect(Collectors.toList());
	}
	
	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Category ID is null");
			return;
		}
		categoryRepository.deleteById(id);
	}
}
