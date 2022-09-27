package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.CommandeFournisseurApi;
import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import com.tn.GestiondeStock.entities.EtatCommande;
import com.tn.GestiondeStock.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto saveCommandeFournisseur(CommandeFournisseurDto dto) {
        return commandeFournisseurService.save(dto);
    }

    @Override
    public CommandeFournisseurDto updateEtatCommandeFournisseur(Integer idCommande, EtatCommande etatCommande) {
        return commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
    }

    @Override
    public CommandeFournisseurDto updateQuantiteCommandeFournisseur(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);
    }

    @Override
    public CommandeFournisseurDto updateFournisseurInCommandeFournisseur(Integer idCommande, Integer idFournisseur) {
        return commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
    }

    @Override
    public CommandeFournisseurDto updateArticleInCommandeFournisseur(Integer idCommande, Integer idLigneCommande, Integer idArticle) {
        return commandeFournisseurService.updateArticle(idCommande, idLigneCommande, idArticle);
    }

    @Override
    public CommandeFournisseurDto deleteArticleInCommandeFournisseur(Integer idCommande, Integer idLigneCommande) {
        return commandeFournisseurService.deleteArticle(idCommande, idLigneCommande);
    }

    @Override
    public CommandeFournisseurDto findCommandeFournisseurById(Integer id) {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findCommandeFournisseurByCode(String code) {
        return commandeFournisseurService.findByCode(code);
    }

    @Override
    public List<CommandeFournisseurDto> findAllCommandeFournisseur() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public void deleteCommandeFournisseur(Integer id) {
        commandeFournisseurService.delete(id);
    }
}
