package com.tn.GestiondeStock.services;

import java.util.List;

import com.tn.GestiondeStock.dto.CommandeClientDto;

public interface CommandeClientService {

	CommandeClientDto save (CommandeClientDto dto);
	
	CommandeClientDto findById (Integer id);
	
	CommandeClientDto findByCode (String code);
	
	List<CommandeClientDto> findAll();
	
	void delete(Integer id );
}
