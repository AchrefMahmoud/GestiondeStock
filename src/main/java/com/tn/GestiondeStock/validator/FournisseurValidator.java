package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.FournisseurDto;

public class FournisseurValidator {

	public static List<String> validate (FournisseurDto fournisseurDto) {
	List<String> errors = new ArrayList<>();
	
	if (fournisseurDto == null ) {
		errors.add("Veuillez renseigner touts les champs du fournisseur");
		return errors;
	}
	
	if (!StringUtils.hasLength(fournisseurDto.getNom())) {
		errors.add("Veuillez renseigner le nom du fournisseur");
	}
	
	if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
		errors.add("Veuillez renseigner le prenom du fournisseur");
	}
	
	if (!StringUtils.hasLength(fournisseurDto.getMail())) {
		errors.add("Veuillez renseigner le mail du fournisseur");
	}
	
	if (!StringUtils.hasLength(fournisseurDto.getNumTel())) {
		errors.add("Veuillez renseigner le numéro de telephone du fournisseur");
	}

	if (!StringUtils.hasLength(fournisseurDto.getAdresse())) {
		errors.add("Veuillez renseigner l'adresse du fournisseur");
	}

	if (!StringUtils.hasLength(fournisseurDto.getPays())) {
		errors.add("Veuillez renseigner la pays du fournisseur");
	}

	if (!StringUtils.hasLength(fournisseurDto.getVille())) {
		errors.add("Veuillez renseigner la ville du fournisseur");
	}

	if (!StringUtils.hasLength(fournisseurDto.getCodePostale())) {
		errors.add("Veuillez renseigner le code postale du fournisseur");
	}

	return errors;

	}
}
