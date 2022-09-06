package com.tn.GestiondeStock.services.impl;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.dto.RoleDto;
import com.tn.GestiondeStock.repository.RoleRepository;
import com.tn.GestiondeStock.dto.UtilisateurDto;
import com.tn.GestiondeStock.services.UtilisateurService;
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
	private UtilisateurService utilisateurService;
	private RoleRepository roleRepository;

	public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService,
								 RoleRepository roleRepository) {
		this.entrepriseRepository = entrepriseRepository;
		this.utilisateurService = utilisateurService;
		this.roleRepository = roleRepository;
	}

	@Override
	public EntrepriseDto save(EntrepriseDto dto) {
		List<String> errors = EntrepriseValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Entreprise is not valid", dto);
			throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
		}
		EntrepriseDto savedEntreprise = EntrepriseDto.fromEntity(
				entrepriseRepository.save(EntrepriseDto.toEntity(dto))
		);

		UtilisateurDto utilisateur = fromEntreprise(savedEntreprise);

		UtilisateurDto saveUser = utilisateurService.save(utilisateur);

		RoleDto roleDto = RoleDto.builder()
				.roleNom("Admin")
				.utilisateur(saveUser)
				.build();

		roleRepository.save(RoleDto.toEntity(roleDto));

		return savedEntreprise;
	}

	private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
		return UtilisateurDto.builder()
				.adresse(dto.getAdresse())
				.nom(dto.getNom())
				.prenom(dto.getCodeFiscal())
				.mail(dto.getMail())
				.MotDePasse(generateRandomPassword())
				.entreprise(dto)
				.dateDeNaissance(Instant.now())
				.photo(dto.getPhoto())
				.pays("pays")
				.ville("ville")
				.codePostale("codePostal")
				.build();
	}

	private String generateRandomPassword() {return "$2a$12$VpEE4sId1zrOm9hCnn0epeMaB0wCanI1Fie5WxVPkY9pdcEKP53sO";}



	@Override
	public EntrepriseDto findById(Integer id) {
		if (id == null) {
			log.error("Entreprise ID is null");
			return null;
		}
		
		return entrepriseRepository.findById(id)
				.map(EntrepriseDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
				"Aucun entreprise avec l'ID =" + id + "n'ete trouve dans la BBD", 
				ErrorCodes.ENTREPRISE_NOT_FOUND)
		); 
	}
	
	
	@Override
	public List<EntrepriseDto> findAll() {
		return entrepriseRepository.findAll().stream()
		 .map(EntrepriseDto::fromEntity)
		 .collect(Collectors.toList());
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
