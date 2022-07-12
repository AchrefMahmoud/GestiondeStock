package com.tn.GestiondeStock.config;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.FlickrApi.FlickrPerm;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

 // @Configuration  -> juste la 1ere fois pr la configuration, on peut meme supprimé tte la classe

@Configuration
public class FlickrConfiguration {

	@Value("${apiKey}")
	private String apiKey;
	
	@Value("${apiSecret}")
	private String apiSecret;
	
	@Value("${appKey}")
	private String appKey;
	
	@Value("${appSecret}")
	private String appSecret;
	
/**	@Bean // on doit ajouter bean pour que au demarrage de l'app spring doit exuceter la methode suivante
	public Flickr getFlickr() throws InterruptedException, ExecutionException, IOException, FlickrException {
		Flickr flickr = new Flickr(apiKey, apiSecret, new REST()); // creer une instance de type flickr en utilisant apiKey et apiSecre et j passe u n param de type rest
		
		OAuth10aService service = new ServiceBuilder(apiKey)
				.apiSecret(apiSecret)
				.build(FlickrApi
				.instance(FlickrPerm.DELETE));
		
		final Scanner scanner = new Scanner (System.in); // car je vais utiliser l'autorisation qui sera fourni par flickr pr activer ou generer mon app token et app seret  
		
		final OAuth1RequestToken request = service.getRequestToken(); 
			
		final String authUrl = service.getAuthorizationUrl(request);
		
		System.out.println(authUrl);
		System.out.println("Paste it here >> ");
		
		final String authVerifier = scanner.nextLine();
		
		OAuth1AccessToken accessToken = service.getAccessToken(request , authVerifier);
		
		System.out.println(accessToken.getToken());
		System.out.println(accessToken.getTokenSecret());
		 
		Auth auth = flickr.getAuthInterface().checkToken(accessToken);
		
		System.out.println("--------------------------");
		System.out.println(auth.getToken());
		System.out.println(auth.getTokenSecret());

		return flickr;
	}
	*/

	@Bean
	public Flickr getFlickr() {
		Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
		
		Auth auth = new Auth();
		
		auth.setPermission(Permission.DELETE);
		
		auth.setToken(apiKey);
		auth.setTokenSecret(appSecret);
		
		RequestContext requestContext = RequestContext.getRequestContext();
		requestContext.setAuth(auth);
		
		flickr.setAuth(auth);
		return flickr;
	}
}
