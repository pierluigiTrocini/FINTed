package it.unical.demacs.enterprise.fintedapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.KeycloakService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class AuthController {

	private final KeycloakService keycloakService;
	
	@PostMapping("/login")
	public ResponseEntity<AccessTokenResponse> login(@RequestParam String username, @RequestParam String password) throws MalformedURLException, IOException{
		return ResponseEntity.ok(keycloakService.login(username, password));
	}
	
	@PostMapping("/logout")
	public void logout(@RequestHeader(value="Authorization") String token) {
		keycloakService.logout(token);
	}
	
}
