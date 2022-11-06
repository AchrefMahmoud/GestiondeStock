package com.tn.GestiondeStock.controller.api;

import static com.tn.GestiondeStock.utils.Constants.UTILISATEUR_ENDPOINT;

import com.tn.GestiondeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.tn.GestiondeStock.dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("utilisateurs")
public interface UtilisateurApi {

    @PostMapping(UTILISATEUR_ENDPOINT + "/create")
    UtilisateurDto saveUtilisateur(@RequestBody UtilisateurDto dto);

    @GetMapping(UTILISATEUR_ENDPOINT + "/{idUtilisateur}")
    UtilisateurDto findUtilisateurById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT + "/all")
    List<UtilisateurDto> findAllUtilisateur();

    @DeleteMapping(UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
    void deleteUtilisateur(@PathVariable("idUtilisateur") Integer id);

    @PostMapping(UTILISATEUR_ENDPOINT + "/getbyemail")
    UtilisateurDto findAllByEmail(@RequestBody String email);

        @PostMapping(UTILISATEUR_ENDPOINT + "/changermotdepasse")
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);
}
