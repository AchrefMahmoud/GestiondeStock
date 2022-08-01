package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;
import static com.tn.GestiondeStock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save (@RequestBody CommandeFournisseurDto dto);

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournisseurDto findById (@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFoutnisseur}")
    CommandeFournisseurDto findByCode (@PathVariable("codeCommandeFoutnisseur") String code);

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id );

}
