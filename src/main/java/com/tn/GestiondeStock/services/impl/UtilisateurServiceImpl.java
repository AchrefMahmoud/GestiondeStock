package com.tn.GestiondeStock.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tn.GestiondeStock.dto.ChangerMotDePasseUtilisateurDto;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private UtilisateurRepository utilisateurRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
		this.utilisateurRepository = utilisateurRepository;
		this.passwordEncoder = passwordEncoder;

	}
	
	@Override
	public UtilisateurDto save(UtilisateurDto dto) {
		List<String> errors = UtilisateurValidator.validate(dto);
		if (!errors.isEmpty()) {
			log.error("Utilisateur is not valid", dto);
			throw new InvalidEntityException("L'utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALID, errors);
		}

		if (userAlreadyExistes(dto.getMail())) {
			throw new InvalidEntityException("Une autre utilisateur avec le meme email existe deja", ErrorCodes.UTILISATEUR_ALREADY_EXIST);
		}

		dto.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));

		return UtilisateurDto.fromEntity
				(utilisateurRepository.save
						(UtilisateurDto.toEntity(dto)));
	}

	private boolean userAlreadyExistes(String email) {
		Optional<Utilisateur> user = utilisateurRepository.findUtilisateurByMail(email);
		return user.isPresent();
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
		return utilisateurRepository.findAll().stream()
		 .map(UtilisateurDto::fromEntity)
		 .collect(Collectors.toList());
	}

	
	@Override
	public void delete(Integer id) {
		if (id == null) {
			log.error("Utilisateur ID is null");
			return;
		}
		utilisateurRepository.deleteById(id);
	}


	@Override
	public UtilisateurDto findByMail(String mail) {
		return utilisateurRepository.findUtilisateurByMail(mail)
				.map(UtilisateurDto::fromEntity)
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun utilisateur avec l'email = " + mail + "n'ete trouve dans la BDD",
				ErrorCodes.UTILISATEUR_NOT_FOUND)
		);
	}


	@Override
	public UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto) {
		validate(dto);
		Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findById(dto.getId());
		if (!utilisateurOptional.isPresent()) {
			log.warn("Aucun utilisateur n'a ete trouve avec l'ID" + dto.getId());
			throw new EntityNotFoundException("Aucun utilisateur n'a ete trouve avec l'ID" + dto.getId(),
					ErrorCodes.UTILISATEUR_NOT_FOUND);
		}

		Utilisateur utilisateur = utilisateurOptional.get();
		utilisateur.setMotDePasse(dto.getMotDePasse());

		return UtilisateurDto.fromEntity(
				utilisateurRepository.save(utilisateur)
		);
	}


	private void validate(ChangerMotDePasseUtilisateurDto dto) {
		if (dto == null) {
			log.warn("Impossible de modifier le mot de passe avec un objet NULL");
			throw new InvalidOperationException("Aucune information n'a ete fourni pour avoir changer le mot de passe",
					ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
		}
		if (dto.getId() == null) {
			log.warn("Impossible de modifier le mot de passe avec un ID NULL");
			throw new InvalidOperationException("ID utilisateur NULL -> impossible de changer le mot de passe",
					ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
		}
		if (!StringUtils.hasLength(dto.getMotDePasse()) || !StringUtils.hasLength(dto.getConfirmMotDePasse())) {
			log.warn("Impossible de modifier le mot de passe avec un mot de passe NULL");
			throw new InvalidOperationException("Mot de passe utilisateur NULL -> impossible de changer le mot de passe",
					ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
		}
		if (!dto.getMotDePasse().equals(dto.getConfirmMotDePasse())) {
			log.warn("Impossible de modifier le mot de passe avec deux mot de passe different");
			throw new InvalidOperationException("Mots de passe utilisateur non conformes -> impossible de changer le mot de passe",
					ErrorCodes.UTILISATEUR_CHANGE_PASSWORD_NOT_VALID);
		}
	}
}
