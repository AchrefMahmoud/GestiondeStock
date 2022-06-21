package com.tn.GestiondeStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.CommandeFournisseur;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

	Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

}
