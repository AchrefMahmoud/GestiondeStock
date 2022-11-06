package com.tn.GestiondeStock.services.impl;

import java.io.InputStream;

import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.tn.GestiondeStock.services.FlickrService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FlickrServiceImpl implements FlickrService {

	@Value("${apiKey}")
	private String apiKey;

	@Value("${apiSecret}")
	private String apiSecret;

	@Value("${appKey}")
	private String appKey;

	@Value("${appSecret}")
	private String appSecret;

	private Flickr flickr;

	@Autowired
	public FlickrServiceImpl(Flickr flickr) {
		this.flickr = flickr;
	}

	@Override
	public String savePhoto(InputStream photo, String title) throws FlickrException {
		Flickr flickr = new Flickr(apiKey, apiSecret, new REST());

		Auth auth = new Auth();

		auth.setPermission(Permission.DELETE);

		auth.setToken(apiKey);
		auth.setTokenSecret(appSecret);

		RequestContext requestContext = RequestContext.getRequestContext();
		requestContext.setAuth(auth);

		flickr.setAuth(auth);


		UploadMetaData uploadMetaData = new UploadMetaData();
		uploadMetaData.setTitle(title);

		String photoId = flickr.getUploader().upload(photo, uploadMetaData);
		return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
	}

}
