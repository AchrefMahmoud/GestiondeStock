package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.entities.CommandeClient;
import com.tn.GestiondeStock.entities.CommandeFournisseur;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.repository.CommandeFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tn.GestiondeStock.dto.FournisseurDto;
import com.tn.GestiondeStock.entities.Fournisseur;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidEntityException;
import com.tn.GestiondeStock.repository.FournisseurRepository;
import com.tn.GestiondeStock.services.FournisseurService;
import com.tn.GestiondeStock.validator.FournisseurValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

	private FournisseurRepository fournisseurRepository;
	private CommandeFournisseurRepository commandeFournisseurRepository;

	@Autowired
	public FournisseurServiceImpl(FournisseurRepository fournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository) {
		this.fournisseurRepository = fournisseurRepository;
		this.commandeFournisseurRepository = commandeFournisseurRepository;
	}

	@Override
	public FournisseurDto save(FournisseurDto dto) {
		List<String> errors = FournisseurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Fournisseur is not valid", dto);
			throw new InvalidEntityException("Le fournisseur n'est pas valide", ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
		}
		return FournisseurDto.fromEntity(fournisseurRepository.save(FournisseurDto.toEntity(dto)));
	}
	
	@Override
	public FournisseurDto findById(Integer id) {
		if (id == null) {
			log.error("Fournisseur ID is null");
			return null;
		}
		
		Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id); 
				
		return Optional.of(FournisseurDto.fromEntity(fournisseur.get())).orElseThrow(() -> 
		new EntityNotFoundException(
				"Aucun fournisseur avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.FOURNISSEUR_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<FournisseurDto> findAll() {
		return fournisseurRepository.findAll().stream()
		 .map(FournisseurDto::fromEntity)
		 .collect(Collectors.toList());
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Fournisseur ID is null");
			return;
		}
		List<CommandeFournisseur> commandeFournisseurs = commandeFournisseurRepository.findAllByFournisseurId(id);
		if (!commandeFournisseurs.isEmpty()) {
			throw new InvalidOperationException("impossible de supprimer un fournisseur deja utiliser",
					ErrorCodes.FOURNISSEUR_ALREADY_IN_USE);
		}
		fournisseurRepository.deleteById(id);
	}
	
}
