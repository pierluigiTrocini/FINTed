package it.unical.demacs.enterprise.fintedapp.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Setter;

@Configuration
@Setter
public class KeycloakConfig {

	@Value("${keycloak.adminClientId}")
	private String adminClientId;
	
	@Value("${keycloak.adminClientSecret}")
	private String adminClientSecret;
	
	@Value("${keycloak.urls.auth}")
	private String authServerUrl;
	
	@Value("${keycloak.client}")
	private String client;
	
	@Value("${keycloak.secret}")
	private String clientSecret;
	
	@Value("${keycloak.realm}")
	private String realm;
	
	
	@Bean
	public Keycloak keycloak() {
		return KeycloakBuilder.builder()
				.serverUrl(authServerUrl)
				.realm(realm)
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(adminClientId)
				.clientSecret(adminClientSecret)
				.build();
	}
	
}
