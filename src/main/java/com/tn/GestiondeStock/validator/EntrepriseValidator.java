package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.EntrepriseDto;

public class EntrepriseValidator {

	
	public static List<String> validate (EntrepriseDto entrepriseDto) {
		List<String> errors = new ArrayList<>();
		
		if (entrepriseDto == null ) {
			errors.add("Veuillez renseigner touts les champs de l'entreprise");
			return errors;
		}
		
		if (!StringUtils.hasLength(entrepriseDto.getNom())) {
			errors.add("Veuillez renseigner le nom du l'entreprise");
		}

		if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
			errors.add("Veuillez renseigner le code fiscale de l'entreprise");
		}
		
		if (!StringUtils.hasLength(entrepriseDto.getMail())) {
			errors.add("Veuillez renseigner le mail du client");
		}
		
		if (!StringUtils.hasLength(entrepriseDto.getNumTel())) {
			errors.add("Veuillez renseigner le numéro de telephone de l'entreprise");
		}

		if (!StringUtils.hasLength(entrepriseDto.getAdresse())) {
			errors.add("Veuillez renseigner l'adresse de l'entreprise");
		}

		if (!StringUtils.hasLength(entrepriseDto.getVille())) {
			errors.add("Veuillez renseigner la ville de l'entreprise");
		}

		if (!StringUtils.hasLength(entrepriseDto.getCodePostale())) {
			errors.add("Veuillez renseigner le code postale de l'entreprise");
		}

		if (!StringUtils.hasLength(entrepriseDto.getPays())) {
			errors.add("Veuillez renseigner la pays de l'entreprise");
		}
		
		return errors;
	}
}
