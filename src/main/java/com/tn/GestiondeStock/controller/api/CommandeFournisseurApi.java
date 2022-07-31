package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;
import static com.tn.GestiondeStock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save (@RequestBody CommandeFournisseurDto dto);

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournisseurDto findById (@PathVariable("idCommandeFournisseur") Integer id);

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFoutnisseur}")
    CommandeFournisseurDto findByCode (@PathVariable("codeCommandeFoutnisseur") String code);

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id );

}
