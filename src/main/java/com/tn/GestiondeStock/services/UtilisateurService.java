package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.UtilisateurDto;


public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);
	
	UtilisateurDto findById(Integer id);
	
	List<UtilisateurDto> findAll();
	
	void delete(Integer id);
}
