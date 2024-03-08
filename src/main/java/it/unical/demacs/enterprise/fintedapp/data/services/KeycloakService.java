package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

import it.unical.demacs.enterprise.fintedapp.data.entities.User;

public interface KeycloakService {

	AccessTokenResponse createKeycloakUser(User user, String password) throws MalformedURLException, IOException;
	
	UserRepresentation getUserById(String userId);
	
	void deleteUser(String username);
	
	AccessTokenResponse getAccessToken(String username, String password) throws MalformedURLException, IOException;
	
}
