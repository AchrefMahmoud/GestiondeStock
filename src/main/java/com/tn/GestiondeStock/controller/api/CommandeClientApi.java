package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CommandeClientDto;
import com.tn.GestiondeStock.dto.LigneCommandeClientDto;
import com.tn.GestiondeStock.entities.CommandeClient;
import com.tn.GestiondeStock.entities.EtatCommande;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;


@Api("commandesclients")
public interface CommandeClientApi {

    @PostMapping(APP_ROOT + "/commandesclients/create")
    ResponseEntity <CommandeClientDto> saveCommandeClient (@RequestBody CommandeClientDto dto);

    @PatchMapping(APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}")
    ResponseEntity <CommandeClientDto> updateEtatCommandeClient (@PathVariable("idCommande") Integer idCommande, @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
    ResponseEntity<CommandeClientDto> updateQuantiteCommandeClient(@PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}")
    ResponseEntity<CommandeClientDto> updateClientInCommandeClient(@PathVariable("idCommande") Integer idCommande, @PathVariable("idClient") Integer idClient);

    @PatchMapping(APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
    ResponseEntity<CommandeClientDto> updateArticleInCommandeClient(@PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande, @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
    ResponseEntity<CommandeClientDto> deleteArticleInCommandeClient(@PathVariable("idCommande")Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity <CommandeClientDto> findCommandeClientById (@PathVariable Integer idCommandeClient);

    @GetMapping(APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity <CommandeClientDto> findCommandeClientByCode (@PathVariable("codeCommandeClient") String code);

  //  @GetMapping(APP_ROOT + "/commandesclients/lignesCommande/{idCommande}")
  //  ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

    @GetMapping(APP_ROOT + "/commandesclients/all")
    ResponseEntity <List<CommandeClientDto>> findAllCommandeClient();

   @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity<Void> deleteCommandeClient(@PathVariable("idCommandeClient") Integer id );

}
