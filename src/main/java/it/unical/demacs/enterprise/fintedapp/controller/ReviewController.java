package it.unical.demacs.enterprise.fintedapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
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
	
	@PostMapping("/")
	public ResponseEntity<ReviewDto> save(@RequestBody ReviewDto review) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.save(review));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		reviewService.delete(id);
	}
	
	@GetMapping("/target/{id}")
	public ResponseEntity<List<ReviewDto>> getTargetReviews(@PathVariable("id") Long id) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.getTargetReviews(id));
	}

	@GetMapping("/author/{id}")
	public ResponseEntity<List<ReviewDto>> getAuthorReviews(@PathVariable("id") Long id) throws ElementNotFoundException{
		return ResponseEntity.ok(reviewService.getAuthorReviews(id));
	}
	
}
