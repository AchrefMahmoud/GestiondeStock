package com.tn.GestiondeStock.validator;

import com.tn.GestiondeStock.dto.CategoryDto;
import com.tn.GestiondeStock.dto.MouvementStockDto;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MouvementStockValidator {

    public static List<String> validate (MouvementStockDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner la date de mouvement");
            errors.add("Veuillez renseigner la quantite de mouvement");
            errors.add("Veuillez renseigner l'article");
            errors.add("Veuillez renseigner le type de mouvement");
            return errors;
        }

        if (dto.getDateMvt() == null) {
            errors.add("Veuillez renseigner la date de mouvement");
        }

        if (dto.getQuantite() == null || dto.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
            errors.add("Veuillez renseigner la quantite de mouvement");
        }

        if (dto.getArticle() == null || dto.getArticle().getId() == null) {
            errors.add("Veuillez renseigner l'article");
        }

        if (!StringUtils.hasLength(dto.getTypemvt().name())) {
            errors.add("Veuillez renseigner le type de mouvement");
        }

        return errors;
    }

}
