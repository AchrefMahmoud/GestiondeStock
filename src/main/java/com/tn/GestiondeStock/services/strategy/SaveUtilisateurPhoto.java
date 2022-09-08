package com.tn.GestiondeStock.services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tn.GestiondeStock.dto.EntrepriseDto;
import com.tn.GestiondeStock.dto.UtilisateurDto;
import com.tn.GestiondeStock.entities.Utilisateur;
import com.tn.GestiondeStock.exception.ErrorCodes;
import com.tn.GestiondeStock.exception.InvalidOperationException;
import com.tn.GestiondeStock.services.FlickrService;
import com.tn.GestiondeStock.services.UtilisateurService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("utilisateurStrategy")
@Slf4j
public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto> {

    private FlickrService flickrService;
    private UtilisateurService utilisateurService;

    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        UtilisateurDto utilisateur  = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur",
                    ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        utilisateur.setPhoto(urlPhoto);
        return utilisateurService.save(utilisateur);
    }
}
