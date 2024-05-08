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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.UserService;
import it.unical.demacs.enterprise.fintedapp.dto.UserDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.InvalidArgumentException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<AccessTokenResponse> save(@RequestBody UserRegistrationDto user) throws MalformedURLException, CredentialsAlreadyUsedException, IOException{
		return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public void delete(@PathVariable("username") String username) throws ElementNotFoundException {
		userService.delete(username);
	}
	
	@PutMapping("/")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#user.getUsername())")
	public ResponseEntity<UserPersonalProfileDto> update(@RequestBody UserPersonalProfileDto user) throws ElementNotFoundException{
		return ResponseEntity.ok(userService.update(user));
	}
	
	@GetMapping("/personal/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public ResponseEntity<UserPersonalProfileDto> getPersonal(@PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(userService.getPersonal(username));
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<List<UserDto>> getAll(@PathVariable("page") Integer page){
		return ResponseEntity.ok(userService.getAll(page));
	}
	
	@GetMapping("/search/{content}")
	public ResponseEntity<List<UserDto>> searchUser(@PathVariable("content") String content){
		return ResponseEntity.ok(userService.searchByUsername(content));
	}
	
	@PostMapping("/rating/{username}/{target}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public void updateRating(@RequestParam Integer ratingValue, @PathVariable("username") String username, @PathVariable("target") String target) throws ElementNotFoundException, InvalidArgumentException {
		userService.updateRating(ratingValue, null, null);
	}
	
	@GetMapping("/{username}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<UserProfileDto> get(@PathVariable("username") String username) throws ElementNotFoundException {
		return ResponseEntity.ok(userService.get(username));
	}

}











