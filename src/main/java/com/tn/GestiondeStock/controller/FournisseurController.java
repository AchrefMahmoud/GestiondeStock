package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.FournisseurApi;
import com.tn.GestiondeStock.dto.FournisseurDto;
import com.tn.GestiondeStock.services.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FournisseurController implements FournisseurApi {

    private FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public FournisseurDto saveFournisseur(FournisseurDto dto) {
        return fournisseurService.save(dto);
    }

    @Override
    public FournisseurDto findFournisseurById(Integer id) {
        return fournisseurService.findById(id);
    }

    @Override
    public List<FournisseurDto> findAllFournisseur() {
        return fournisseurService.findAll();
    }

    @Override
    public void deleteFournisseur(Integer id) {
        fournisseurService.delete(id);
    }
}
