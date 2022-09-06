package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.LigneCommandeClient;

import java.util.Arrays;
import java.util.List;

public interface LigneCommandeClientRepository extends JpaRepository<LigneCommandeClient, Integer> {

    //List<LigneCommandeClient> findAllByCommandeClientId(Integer id);

}
