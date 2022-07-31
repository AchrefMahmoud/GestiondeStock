package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/commandesclients")
public interface CommandeClientApi {

    @PostMapping(APP_ROOT + "/commandesclients/create")
    ResponseEntity <CommandeClientDto> save (@RequestBody CommandeClientDto dto);

    @PostMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity <CommandeClientDto> findById (@PathVariable Integer idCommandeClient);

    @PostMapping(APP_ROOT + "/commandesclients/{codeCommandeClient}")
    ResponseEntity <CommandeClientDto> findByCode (@PathVariable("codeCommandeClient") String code);

    @PostMapping(APP_ROOT + "/commandesclients/all")
    ResponseEntity <List<CommandeClientDto>> findAll();

   @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id );

}
