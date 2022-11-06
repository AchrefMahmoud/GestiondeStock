package com.tn.GestiondeStock.controller.api;

import java.util.List;

import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneVenteDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tn.GestiondeStock.dto.ArticleDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;

@Api("articles")
public interface ArticleApi {


	@PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Enregistrer un article", notes = "Cette methode permet d'enregistrer ou modifier un article", response = ArticleDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'objet article creer / modifier"),
			@ApiResponse(code = 400, message = "L'objet article n'est pas valide")
	}) 
	ArticleDto saveArticle (@RequestBody ArticleDto dto);
	
	@GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher un article par ID", notes = "Cette methode permet de chercher un article par son ID", response = ArticleDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'article a ete trouver dans la BDD"),
			@ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec l'ID fourni")
	})
	ArticleDto findArticleById (@PathVariable("idArticle") Integer id);
	
	@GetMapping(value = APP_ROOT + "/articles/findByCode/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Rechercher un article par CODE", notes = "Cette methode permet de chercher un article par son CODE", response = ArticleDto.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'article a ete trouver dans la BDD"),
			@ApiResponse(code = 404, message = "Aucun article n'existe dans la BDD avec le CODE fourni")
	})
	ArticleDto findArticleByCodeArticle (@PathVariable("codeArticle") String codeArticle);

	@GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Renvoir la listes des articles", notes = "Cette methode permet de chercher et renvoiyer la liste des articles existent dans la BDD", responseContainer = "List<ArticleDto>")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "La liste des articles / Une liste vide"),
			})
	List<ArticleDto> findAllArticle();



	@GetMapping(value = APP_ROOT + "/articles/historique/vente/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<LigneVenteDto> findHistoriqueVentesArticle(@PathVariable("idArticle") Integer idArticle);

	@GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<LigneCommandeClientDto> findHistoriqueCommandeClientArticle(@PathVariable("idArticle") Integer idArticle);

	@GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseurArticle(@PathVariable("idArticle") Integer idArticle);

	@GetMapping(value = APP_ROOT + "/articles/filtre/category/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
	List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);



	@DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
	@ApiOperation(value = "Supprimer un article par CODE", notes = "Cette methode permet de spprimer un article par son ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "L'article a ete supprimer"),
	})
	void deleteArticle( @PathVariable("idArticle") Integer id );
	
}
