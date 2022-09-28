package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.MouvementStockApi;
import com.tn.GestiondeStock.dto.MouvementStockDto;
import com.tn.GestiondeStock.services.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin
public class MouvementStockController implements MouvementStockApi {

    private MouvementStockService service;

   @Autowired
    public MouvementStockController(MouvementStockService service) {
        this.service = service;
    }

    @Override
    public BigDecimal stockReelArticle(Integer idArticle) {
       return service.stockReelArticle(idArticle);
    }

    @Override
    public List<MouvementStockDto> mvtStkArticle(Integer idArticle) {
       return service.mvtStkArticle(idArticle);
    }

    @Override
    public MouvementStockDto entreeStock(MouvementStockDto dto) {
        return service.entreeStock(dto);
    }

    @Override
    public MouvementStockDto sortieStock(MouvementStockDto dto) {
        return service.sortieStock(dto);
    }

    @Override
    public MouvementStockDto correctionStockPos(MouvementStockDto dto) {
       return service.correctionStockPos(dto);
    }

    @Override
    public MouvementStockDto correctionStockNeg(MouvementStockDto dto) {
       return service.correctionStockNeg(dto);
    }
}
