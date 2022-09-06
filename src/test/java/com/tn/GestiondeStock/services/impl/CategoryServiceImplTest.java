package com.tn.GestiondeStock.services.impl;

import com.tn.GestiondeStock.dto.CategoryDto;
import com.tn.GestiondeStock.services.CategoryService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService service;

    @Test
    public void shouldSaveCategoryWithSuccess() {
        CategoryDto expectedCategory = CategoryDto.builder()
                .codeCategory("cat tesst")
                .designation("Designation tesst")
                .idEntreprise(1)
                .build();

        CategoryDto savedCategory = service.save(expectedCategory);

        assertNotNull(savedCategory);

        assertNotNull(savedCategory.getId());

        Assertions.assertEquals(expectedCategory.getCodeCategory(), savedCategory.getCodeCategory());

        Assertions.assertEquals(expectedCategory.getDesignation(), savedCategory.getDesignation());

        Assertions.assertEquals(expectedCategory.getIdEntreprise(), savedCategory.getIdEntreprise());
    }


}