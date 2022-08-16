package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Utilisateur;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query(value = "select u from Utilisateur u where u.mail = :mail")
    Optional <Utilisateur> findUtilisateurByMail(String mail);

}
