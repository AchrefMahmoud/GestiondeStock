package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CommandeClientDto;
import com.tn.GestiondeStock.dto.CommandeFournisseurDto;
import com.tn.GestiondeStock.dto.LigneCommandeFournisseurDto;
import com.tn.GestiondeStock.entities.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;
import static com.tn.GestiondeStock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;

@Api(COMMANDE_FOURNISSEUR_ENDPOINT)
public interface CommandeFournisseurApi {

    @PostMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/create")
    CommandeFournisseurDto save (@RequestBody CommandeFournisseurDto dto);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}")
    CommandeFournisseurDto updateEtatCommande(@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);


    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
    CommandeFournisseurDto updateQuantiteCommande(@PathVariable("idCommande") Integer idCommande,
        @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}")
    CommandeFournisseurDto updateFournisseur(@PathVariable("idCommande") Integer idCommande, @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
    CommandeFournisseurDto updateArticle(@PathVariable("idCommande") Integer idCommande,
        @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}")
    CommandeFournisseurDto deleteArticle(@PathVariable("idCommande")Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);


    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{idCommandeFournisseur}")
    CommandeFournisseurDto findById (@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/{codeCommandeFoutnisseur}")
    CommandeFournisseurDto findByCode (@PathVariable("codeCommandeFoutnisseur") String code);

    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/all")
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/{idCommandeFournisseur}")
    void delete(@PathVariable("idCommandeFournisseur") Integer id );


//    @GetMapping(COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}")
//    List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseur(@PathVariable("idCommande") Integer idCommande);


}
