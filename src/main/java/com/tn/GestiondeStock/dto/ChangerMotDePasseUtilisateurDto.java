package com.tn.GestiondeStock.dto;

import com.tn.GestiondeStock.services.strategy.Strategy;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ChangerMotDePasseUtilisateurDto {

    private Integer id;
    private String motDePasse;
    private String confirmMotDePasse;

}
