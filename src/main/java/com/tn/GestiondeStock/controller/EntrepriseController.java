package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.EntrepriseApi;
import com.tn.GestiondeStock.dto.EntrepriseDto;
import com.tn.GestiondeStock.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @Override
    public EntrepriseDto saveEntreprise(EntrepriseDto dto) {
        return entrepriseService.save(dto);
    }

    @Override
    public EntrepriseDto findEntrepriseById(Integer id) {
        return entrepriseService.findById(id);
    }

    @Override
    public List<EntrepriseDto> findAllEntreprise() {
        return entrepriseService.findAll();
    }

    @Override
    public void deleteEntreprise(Integer id) {
        entrepriseService.delete(id);
    }
}
