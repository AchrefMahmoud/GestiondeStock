package com.tn.GestiondeStock.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.CommandeClient;


public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{

	Optional<CommandeClient> findCommandeClientByCode(String code);

	List<CommandeClient> findAllByClientId(Integer id);

}
