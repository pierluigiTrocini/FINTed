package it.unical.demacs.enterprise.fintedapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.SpeditionService;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/speditions")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class SpeditionController {
	private final SpeditionService speditionService;
	
	@GetMapping("/personal/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public ResponseEntity<List<SpeditionDto>> getPersonal(@PathVariable("username") String username, @RequestHeader(value="Authorization") String token) {
		return ResponseEntity.ok(speditionService.getBySeller(username));
	}
	
}
