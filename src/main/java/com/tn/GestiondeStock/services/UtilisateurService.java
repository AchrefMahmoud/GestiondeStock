package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.tn.GestiondeStock.dto.UtilisateurDto;


public interface UtilisateurService {

	UtilisateurDto save(UtilisateurDto dto);
	
	UtilisateurDto findById(Integer id);
	
	List<UtilisateurDto> findAll();

	UtilisateurDto findByMail(String mail);
	
	void delete(Integer id);

	UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}
