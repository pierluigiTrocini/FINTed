package it.unical.demacs.enterprise.fintedapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
import it.unical.demacs.enterprise.fintedapp.dto.UserDto;
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
	
	@PostMapping("/")
	public ResponseEntity<UserDto> save(@RequestBody UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException{
		return ResponseEntity.ok(userService.save(user));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		userService.delete(id);
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<List<UserProfileDto>> getAll(@PathVariable("page") Integer page){
		return ResponseEntity.ok(userService.getAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> get(@PathVariable("id") Long id) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.get(id));
	}
	
	@GetMapping("/personal/{id}")
	public ResponseEntity<UserPersonalProfileDto> getPersonalProfile(@PathVariable("id") Long id) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.getPersonalProfile(id));
	}
	
	@PutMapping("/")
	public ResponseEntity<UserPersonalProfileDto> update(@RequestBody UserPersonalProfileDto user) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(userService.update(user));
	}
	
	
	
	
}
