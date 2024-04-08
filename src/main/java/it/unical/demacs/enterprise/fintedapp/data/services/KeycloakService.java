package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.net.MalformedURLException;

import org.keycloak.representations.AccessTokenResponse;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;

public interface KeycloakService {

	AccessTokenResponse createKeycloakUser(User user, String password) throws MalformedURLException, IOException;
	
	void deleteUser(String username);
	
	AccessTokenResponse login(String username, String password) throws MalformedURLException, IOException;
	
	void logout(String accessToken);
	
}
