package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.MouvementStock;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Integer>{

}
