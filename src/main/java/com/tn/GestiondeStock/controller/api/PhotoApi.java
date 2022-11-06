package com.tn.GestiondeStock.controller.api;

import com.flickr4java.flickr.FlickrException;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.tn.GestiondeStock.utils.Constants.APP_ROOT;
import static com.tn.GestiondeStock.utils.Constants.PHOTO_ENDPOINT;

@Api("photos")
public interface PhotoApi {

    @PostMapping(PHOTO_ENDPOINT + "/{id}/{title}/{context}")
    Object savePhoto(String context, Integer id, @RequestPart("file") MultipartFile photo, String title) throws IOException, FlickrException;

}
