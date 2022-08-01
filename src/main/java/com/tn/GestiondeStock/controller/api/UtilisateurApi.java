package com.tn.GestiondeStock.controller.api;

import static com.tn.GestiondeStock.utils.Constants.FOURNISSEUR_ENDPOINT;
import static com.tn.GestiondeStock.utils.Constants.UTILISATEUR_ENDPOINT;

import com.tn.GestiondeStock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

    @PostMapping(UTILISATEUR_ENDPOINT + "/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT + "/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
