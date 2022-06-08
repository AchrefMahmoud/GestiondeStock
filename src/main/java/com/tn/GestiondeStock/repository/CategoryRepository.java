package com.tn.GestiondeStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer>{

	 Optional <Category> findCategoryByCodeCategory (String codeCategory);

}
