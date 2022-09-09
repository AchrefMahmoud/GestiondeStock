package com.tn.GestiondeStock.validator;

import java.util.ArrayList;
import java.util.List;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import org.springframework.util.StringUtils;

public class CommandeFournisseurValidator {

	public static List<String> validate (CommandeFournisseurDto dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Veuillez renseinger le code de la commande");
			errors.add("Veuillez renseinger la date de la commande");
			errors.add("Veuillez renseinger l'etat' de la commande");
			errors.add("Veuillez renseinger le fournisseur");
			return errors;
		}


		if (!StringUtils.hasLength(dto.getCode())) {
			errors.add("Veuillez renseinger le code de la commande");
		}

		if (dto.getDateCommande() == null) {
			errors.add("Veuillez renseinger la date de la commande");
		}

		if (!StringUtils.hasLength(dto.getEtatCommande().toString())) {
			errors.add("Veuillez renseinger l'etat' de la commande");
		}

		if (dto.getFournisseur() == null || dto.getFournisseur().getId() == null) {
			errors.add("Veuillez renseinger le fournisseur");
		}

		return errors;

	}
}
