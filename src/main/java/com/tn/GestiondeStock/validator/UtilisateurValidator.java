package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.UtilisateurDto;

public class UtilisateurValidator {
	
	public static List<String> validate(UtilisateurDto utilisateurDto) {
		List<String> errors = new ArrayList<>();
		
		if (utilisateurDto == null) {
			errors.add("Veuillez renseigner touts les champs de l'utilisateur");
			return errors;
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getNom())) {
			errors.add("Veuillez renseigner le nom d'utilisateur");
		}

		if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
			errors.add("Veuillez renseigner le prenom d'utilisateur");
		}

		if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
			errors.add("Veuillez renseigner le mot de passe d'utilisateur");
		}
		
		if (utilisateurDto.getDateDeNaissance() == null ) {
			errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getAdresse())) {
			errors.add("Veuillez renseigner l'adresse d'utilisateur");
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getVille())) {
			errors.add("Veuillez renseigner la ville d'utilisateur");
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getCodePostale())) {
			errors.add("Veuillez renseigner le code postale d'utilisateur");
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getPays())) {
			errors.add("Veuillez renseigner la pays d'utilisateur");
		}
		
		if (!StringUtils.hasLength(utilisateurDto.getMail())) {
			errors.add("Veuillez renseigner l'Email d'utilisateur");
		}
		
		return errors;
	}

}