package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.GestiondeStock.dto.EntrepriseDto;
import com.tn.GestiondeStock.entities.Entreprise;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.EntrepriseRepository;
import com.tn.GestiondeStock.services.EntrepriseService;
import com.tn.GestiondeStock.validator.EntrepriseValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

	private EntrepriseRepository entrepriseRepository;

	@Autowired
	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
		this.entrepriseRepository = entrepriseRepository;
	}
	
	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		List<String> errors = EntrepriseValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Entreprise is not valid", dto);
			throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
		}
		return EntrepriseDto.fromEntity(entrepriseRepository.save(EntrepriseDto.toEntity(dto)));
	}
	
	@Override
	public EntrepriseDto findById(Integer id) {
		if (id == null) {
			log.error("Entreprise ID is null");
			return null;
		}
		
		Optional<Entreprise> entreprise = entrepriseRepository.findById(id); 
				
		return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun entreprise avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.ENTREPRISE_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<EntrepriseDto> findAll() {
		entrepriseRepository.findAll().stream()
		 .map(EntrepriseDto::fromEntity)
		 .collect(Collectors.toList());
		return null;
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Entreprise ID is null");
			return;
		}
		entrepriseRepository.deleteById(id);
	}
	
}
