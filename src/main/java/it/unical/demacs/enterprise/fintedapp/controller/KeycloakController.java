package it.unical.demacs.enterprise.fintedapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;


import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.KeycloakService;
import it.unical.demacs.enterprise.fintedapp.dto.security.Credentials;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/keycloak")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class KeycloakController {
	private final KeycloakService keycloakService;
	
	@PostMapping("/login")
	public ResponseEntity<AccessTokenResponse> login(@RequestBody Credentials credentials) throws MalformedURLException, IOException{
		return ResponseEntity.ok(keycloakService.getAccessToken(credentials.getUsername(), credentials.getPassword()));
	}
	
	@PostMapping("/logout")
	@PreAuthorize("authentication.principal.claims['preferred_username'] == #username")
	public void logout(@RequestParam String username, @RequestParam String accessToken) {
		keycloakService.logout(accessToken);
	}	
	
}
