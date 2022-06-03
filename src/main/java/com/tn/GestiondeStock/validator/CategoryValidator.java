package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.CategoryDto;

public class CategoryValidator {

	public static List<String> validate (CategoryDto categoryDto) {
		List<String> errors = new ArrayList<>();
		
		if (categoryDto == null || !StringUtils.hasLength(categoryDto.getCodeCategory())) {
			errors.add("Veuillez renseigner le code de la categorie");
		}
		return errors;
	}
}
