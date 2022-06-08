package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.EntrepriseDto;

public interface EntrepriseService {

	EntrepriseDto save(EntrepriseDto dto);
	
	EntrepriseDto findById(Integer id);
		
	List<EntrepriseDto> findAll();
	
	void delete(Integer id);
}
