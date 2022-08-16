package com.tn.GestiondeStock.services.auth;

import com.tn.GestiondeStock.dto.UtilisateurDto;
import com.tn.GestiondeStock.entities.Utilisateur;
import com.tn.GestiondeStock.entities.auth.ExtendedUser;
import com.tn.GestiondeStock.exception.EntityNotFoundException;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.repository.UtilisateurRepository;
import com.tn.GestiondeStock.services.UtilisateurService;
import com.tn.GestiondeStock.services.impl.UtilisateurServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;


    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByMail(mail) ;

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        utilisateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleNom())));

        return new ExtendedUser(utilisateur.getMail(), utilisateur.getMotDePasse(), utilisateur.getEntreprise().getId(), authorities);
    }
}
