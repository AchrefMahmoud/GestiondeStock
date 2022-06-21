package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.CommandeFournisseurDto;

public interface CommandeFournisseurService {
	
	CommandeFournisseurDto save (CommandeFournisseurDto dto);
	
	CommandeFournisseurDto findById (Integer id);
	
	CommandeFournisseurDto findByCode (String code);
	
	List<CommandeFournisseurDto> findAll();
	
	void delete(Integer id );
	
}
