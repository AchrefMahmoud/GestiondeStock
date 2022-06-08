package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.CommandeClient;


public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer>{

}
