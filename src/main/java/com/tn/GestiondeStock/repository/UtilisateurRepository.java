package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

}
