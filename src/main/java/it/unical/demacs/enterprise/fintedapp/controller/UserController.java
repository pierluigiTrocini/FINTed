package it.unical.demacs.enterprise.fintedapp.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import it.unical.demacs.enterprise.fintedapp.data.services.UserService;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<AccessTokenResponse> save(@RequestBody UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException, MalformedURLException, IOException{
		return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/{username}")
	public void delete(@PathVariable("username") String username) throws ElementNotFoundException {
		userService.delete(username);
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<List<UserProfileDto>> getAll(@PathVariable("page") Integer page){
		return ResponseEntity.ok(userService.getAll(page));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserProfileDto> get(@PathVariable("username") String username) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.get(username));
	}
	
	@GetMapping("/personal/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'] == #username")
	public ResponseEntity<UserPersonalProfileDto> getPersonalProfile(@PathVariable("username") String username) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.getPersonalProfile(username));
	}
	
	@PutMapping("/")
	@PreAuthorize("authentication.principal.claims['preferred_username'] == #username")
	public ResponseEntity<UserPersonalProfileDto> update(@RequestBody UserPersonalProfileDto user) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.update(user));
	}
	
	
	
	
}
