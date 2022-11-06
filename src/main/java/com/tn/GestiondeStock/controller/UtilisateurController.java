package com.tn.GestiondeStock.controller;

import com.tn.GestiondeStock.controller.api.UtilisateurApi;
import com.tn.GestiondeStock.dto.UtilisateurDto;
import com.tn.GestiondeStock.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tn.GestiondeStock.dto.ChangerMotDePasseUtilisateurDto;

import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto saveUtilisateur(UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }

    @Override
    public UtilisateurDto findUtilisateurById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAllUtilisateur() {
        return utilisateurService.findAll();
    }

    @Override
    public void deleteUtilisateur(Integer id) {
        utilisateurService.delete(id);
    }

    @Override
    public UtilisateurDto findAllByEmail(@RequestBody String email) { return utilisateurService.findByMail(email);
    }

    @Override
    public UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto) { return utilisateurService.changerMotDePasse(dto);
    }
}
