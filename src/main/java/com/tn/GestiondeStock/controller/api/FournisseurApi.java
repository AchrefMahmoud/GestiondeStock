package com.tn.GestiondeStock.controller.api;


import static com.tn.GestiondeStock.utils.Constants.FOURNISSEUR_ENDPOINT;

import com.tn.GestiondeStock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(FOURNISSEUR_ENDPOINT)
public interface FournisseurApi {

    @PostMapping(FOURNISSEUR_ENDPOINT + "/create")
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(FOURNISSEUR_ENDPOINT + "/{idFournisseur}")
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(FOURNISSEUR_ENDPOINT + "/all")
    List<FournisseurDto> findAll();

    @DeleteMapping(FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    void delete(@PathVariable("idFournisseur") Integer id);

}
