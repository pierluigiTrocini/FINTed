package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.KeycloakService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

	@Value("${keycloak.adminClientId}")
	private String adminClient;
	
	@Value("${keycloak.adminClientSecret}")
	private String adminClientSecret;
	
	@Value("${keycloak.urls.auth}")
	private String authServerUrl;
	
	@Value("${keycloak.realm}")
	private String realm;
	
	@Value("${keycloak.client}")
	private String fintedClientId;
	
	@Value("${keycloak.secret}")
	private String fintedClientSecret;
	
	private final Keycloak keycloak;
	
	@Override
	public void createKeycloakUser(User user, String password)
			throws MalformedURLException, IOException {
		UserRepresentation keycloakUser = new UserRepresentation(); 
		keycloakUser.setEnabled(true);
		keycloakUser.setUsername(user.getUsername());
		keycloakUser.setEmail(user.getCredentials().getEmail());
		keycloakUser.setFirstName(user.getFirstName());
		keycloakUser.setLastName(user.getLastName());
		keycloakUser.setEmailVerified(true);
		
		CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
		credentialRepresentation.setValue(password);
		credentialRepresentation.setTemporary(false);
		credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
		
		List<CredentialRepresentation> list = new ArrayList<CredentialRepresentation>();
		list.add(credentialRepresentation);
		
		keycloakUser.setCredentials(list);
		
		keycloak.realm(realm).users().create(keycloakUser);
	}

	@Override
	public void deleteUser(String username) {
		for(UserRepresentation user: keycloak.realm(realm).users().searchByUsername(username, true)) {
			keycloak.realm(realm).users().delete(user.getId());
		}
	}

	@Override
	public AccessTokenResponse login(String username, String password) throws MalformedURLException, IOException {
		return KeycloakBuilder.builder()
				.serverUrl(authServerUrl)
				.realm(realm)
				.clientId(fintedClientId)
				.clientSecret(fintedClientSecret)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.username(username)
				.password(password)
				.grantType(OAuth2Constants.PASSWORD)
				.build()
				.tokenManager()
				.getAccessToken();
	}

	@Override
	public void logout(String accessToken) {
		KeycloakBuilder.builder()
		.serverUrl(authServerUrl)
		.realm(realm)
		.clientId(fintedClientId)
		.clientSecret(fintedClientSecret)
		.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
		.build()
		.tokenManager()
		.invalidate(accessToken);
	}

}
