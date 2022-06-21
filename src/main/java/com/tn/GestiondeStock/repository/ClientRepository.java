package com.tn.GestiondeStock.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tn.GestiondeStock.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

}
