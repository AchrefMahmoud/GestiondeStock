package com.tn.GestiondeStock.controller.api;

import com.tn.GestiondeStock.dto.MouvementStockDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;
import java.util.List;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mvtstk")
public interface MouvementStockApi {

    @GetMapping(APP_ROOT + "/mvtstk/stockreel/{idArticle}")
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(APP_ROOT + "/mvtstk/filter/article/{idArticle}")
    List<MouvementStockDto> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(APP_ROOT + "/mvtstk/entree")
    MouvementStockDto entreeStock(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/sortie")
    MouvementStockDto sortieStock(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/correctionpos")
    MouvementStockDto correctionStockPos(@RequestBody MouvementStockDto dto);

    @PostMapping(APP_ROOT + "/mvtstk/correctionneg")
    MouvementStockDto correctionStockNeg(@RequestBody MouvementStockDto dto);

}
