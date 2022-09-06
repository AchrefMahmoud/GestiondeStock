package com.tn.GestiondeStock.controller.api;
import static com.tn.GestiondeStock.utils.Constants.VENTES_ENDPOINT;

import com.tn.GestiondeStock.dto.VentesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(VENTES_ENDPOINT)
public interface VentesApi {

    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesDto save (@RequestBody VentesDto dto);

    @GetMapping(VENTES_ENDPOINT + "/{idVente}")
    VentesDto findById (@PathVariable("idVente") Integer id);

//  @GetMapping(VENTES_ENDPOINT + "/codeVente"
//	VentesDto findByCode (@PathVariable("idVente") String codeVente);

    @GetMapping(VENTES_ENDPOINT + "/all")
    List<VentesDto> findAll();

    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("idVente")Integer id );
}
