package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.tn.GestiondeStock.dto.ClientDto;

public class ClientValidator {

	public static List<String> validate (ClientDto clientDto) {
		List<String> errors = new ArrayList<>();
		
		if (clientDto == null ) {
			errors.add("Veuillez renseigner touts les champs du client");
			return errors;
		}
		
		if (!StringUtils.hasLength(clientDto.getNom())) {
			errors.add("Veuillez renseigner le nom du client");
		}
		
		if (!StringUtils.hasLength(clientDto.getPrenom())) {
			errors.add("Veuillez renseigner le prenom du client");
		}
		
		if (!StringUtils.hasLength(clientDto.getMail())) {
			errors.add("Veuillez renseigner le mail du client");
		}
		
		if (!StringUtils.hasLength(clientDto.getNumTel())) {
			errors.add("Veuillez renseigner le numéro de telephone du client");
		}

		if (!StringUtils.hasLength(clientDto.getAdresse())) {
			errors.add("Veuillez renseigner l'adresse du client");
		}

		if (!StringUtils.hasLength(clientDto.getPays())) {
			errors.add("Veuillez renseigner la pays du client");
		}

		if (!StringUtils.hasLength(clientDto.getVille())) {
			errors.add("Veuillez renseigner la ville du client");
		}

		if (!StringUtils.hasLength(clientDto.getCodePostale())) {
			errors.add("Veuillez renseigner le code postale du client");
		}
			return errors;
	}
	
}
