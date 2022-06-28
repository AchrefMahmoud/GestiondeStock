package com.tn.GestiondeStock.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.tn.GestiondeStock.entities.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {
	
//	Optional<Ventes> findVentesByCode(String code);
	
}
