package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CategoryDto;
import com.tn.GestiondeStock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/client")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "Cette methode permet d'enregistrer ou modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client creer / modifier"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Cette methode permet de chercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete trouver dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client n'existe dans la BDD avec l'ID fourni")
    })
    ClientDto findById(@PathVariable("idClient") Integer idClient);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoir la listes des client", notes = "Cette methode permet de chercher et renvoiyer la liste des client sexistent dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des client / Une liste vide"),
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer une categorie par CODE", notes = "Cette methode permet de spprimer un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a ete supprimer"),
    })
    void delete(@PathVariable("idClient") Integer id);
}
