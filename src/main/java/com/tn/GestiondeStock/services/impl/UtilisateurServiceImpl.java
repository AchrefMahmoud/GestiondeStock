package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.GestiondeStock.dto.UtilisateurDto;
import com.tn.GestiondeStock.entities.Utilisateur;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.UtilisateurRepository;
import com.tn.GestiondeStock.services.UtilisateurService;
import com.tn.GestiondeStock.validator.UtilisateurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
		this.utilisateurRepository = utilisateurRepository;
	}
	
	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		List<String> errors = UtilisateurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Utilisateur is not valid", dto);
			throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
		}
		return UtilisateurDto.fromEntity(utilisateurRepository.save(UtilisateurDto.toEntity(dto)));
	}
	
	@Override
	public UtilisateurDto findById(Integer id) {
		if (id == null) {
			log.error("Utilisateur ID is null");
			return null;
		}
		
		Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id); 
				
		return Optional.of(UtilisateurDto.fromEntity(utilisateur.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun utilisateur avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.UTILISATEUR_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<UtilisateurDto> findAll() {
		utilisateurRepository.findAll().stream()
		 .map(UtilisateurDto::fromEntity)
		 .collect(Collectors.toList());
		return null;
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Utilisateur ID is null");
			return;
		}
		utilisateurRepository.deleteById(id);
	}

}
