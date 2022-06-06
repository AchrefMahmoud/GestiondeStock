package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Vente;

public interface VenteRepository extends JpaRepository<Integer, Vente> {

}
