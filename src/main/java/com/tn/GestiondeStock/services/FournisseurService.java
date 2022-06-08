package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.FournisseurDto;


public interface FournisseurService {

	FournisseurDto save(FournisseurDto dto);
	
	FournisseurDto findById(Integer id);
	
	List<FournisseurDto> findAll();
	
	void delete(Integer id);
}
