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

import it.unical.demacs.enterprise.fintedapp.data.services.OfferService;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/offers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OfferController {
	private final OfferService offerService;
	
	@PostMapping("/")
	public ResponseEntity<OfferDto> save(@RequestBody OfferDto offer) throws ElementNotFoundException{
		return ResponseEntity.ok(offerService.save(offer));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		offerService.delete(id);
	}
	
	@GetMapping("/post/{id}")
	public ResponseEntity<List<OfferDto>> getPostOffers(@PathVariable("id") Long id) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(offerService.getPostOffers(id));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<OfferDto>> getUserOffers(@PathVariable("id") Long id) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(offerService.getUserOffers(id));
	}
	
}
