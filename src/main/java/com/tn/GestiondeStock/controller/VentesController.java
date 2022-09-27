package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.VentesApi;
import com.tn.GestiondeStock.dto.VentesDto;
import com.tn.GestiondeStock.services.VentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VentesController implements VentesApi {

    private VentesService ventesService;

    @Autowired
    public VentesController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto saveVente(VentesDto dto) {
        return ventesService.save(dto);
    }

    @Override
    public VentesDto findVenteById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public List<VentesDto> findAllVente() {
        return ventesService.findAll();
    }

    @Override
    public void deleteVente(Integer id) {
        ventesService.delete(id);
    }
}
