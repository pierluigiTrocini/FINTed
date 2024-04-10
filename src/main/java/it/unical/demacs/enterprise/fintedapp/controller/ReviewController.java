package it.unical.demacs.enterprise.fintedapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.ReviewService;
import it.unical.demacs.enterprise.fintedapp.dto.ReviewDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class ReviewController {
	private final ReviewService reviewService;
	
	@PostMapping("/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto review, @PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.save(review));
	}
	
	@DeleteMapping("/{username}/{id}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public void delete(@PathVariable("username") String username, @PathVariable("id") Long id) throws ElementNotFoundException {
		reviewService.delete(id, username);
	}
	
	@GetMapping("/target/{username}")
	public ResponseEntity<List<ReviewDto>> getByTarget(@PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.getByTarget(username));
	}
	
	@GetMapping("/personal/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public ResponseEntity<List<ReviewDto>> getPersonal(@PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.getByTarget(username));
	}

}
