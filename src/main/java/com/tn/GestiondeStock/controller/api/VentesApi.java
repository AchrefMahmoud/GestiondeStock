package com.tn.GestiondeStock.controller.api;
import static com.tn.GestiondeStock.utils.Constants.VENTES_ENDPOINT;

import com.tn.GestiondeStock.dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("ventes")
public interface VentesApi {

    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesDto saveVente (@RequestBody VentesDto dto);

    @GetMapping(VENTES_ENDPOINT + "/{idVente}")
    VentesDto findVenteById (@PathVariable("idVente") Integer id);

//  @GetMapping(VENTES_ENDPOINT + "/codeVente"
//	VentesDto findByCode (@PathVariable("idVente") String codeVente);

    @GetMapping(VENTES_ENDPOINT + "/all")
    List<VentesDto> findAllVente();

    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void deleteVente(@PathVariable("idVente")Integer id );
}
