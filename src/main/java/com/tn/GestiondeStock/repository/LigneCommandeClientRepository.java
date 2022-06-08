package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.LigneCommandeClient;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

}
