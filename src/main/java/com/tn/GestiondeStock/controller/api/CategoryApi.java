package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.ArticleDto;
import com.tn.GestiondeStock.dto.CategoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;

@Api("categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette methode permet d'enregistrer ou modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie creer / modifier"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    CategoryDto saveCategory(@RequestBody CategoryDto dto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categories par ID", notes = "Cette methode permet de chercher une categories par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouver dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'ID fourni")
    })
    CategoryDto findCategoryById(@PathVariable("idCategory") String idCategory);

    @GetMapping(value = APP_ROOT + "/categories/findByCode/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Cette methode permet de chercher une categories par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete trouver dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec le CODE fourni")
    })
    CategoryDto findCategoryByCodeCategory(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoir la listes des categories", notes = "Cette methode permet de chercher et renvoiyer la liste des categories sexistent dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide"),
    })
    List<CategoryDto> findAllCategory();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}")
    @ApiOperation(value = "Supprimer une categorie par CODE", notes = "Cette methode permet de spprimer une categorie par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a ete supprimer"),
    })
    void deleteCategory(@PathVariable("idCategory") Integer id );

}
