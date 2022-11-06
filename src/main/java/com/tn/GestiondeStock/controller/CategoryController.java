package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.CategoryApi;
import com.tn.GestiondeStock.dto.CategoryDto;
import com.tn.GestiondeStock.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController implements CategoryApi {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public CategoryDto saveCategory(CategoryDto dto) {
        return categoryService.save(dto);
    }

    @Override
    public CategoryDto findCategoryById(String idCategory) {
        return categoryService.findById(idCategory);
    }

    @Override
    public CategoryDto findCategoryByCodeCategory(String codeCategory) {
        return categoryService.findByCodeCategory(codeCategory);
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        return categoryService.findAll();
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryService.delete(id);
    }
}
