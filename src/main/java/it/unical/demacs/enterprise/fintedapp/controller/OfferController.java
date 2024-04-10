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

import it.unical.demacs.enterprise.fintedapp.data.services.OfferService;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NoFundException;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/offers")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class OfferController {
	private final OfferService offerService;
	
	@PostMapping("/")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#offer.getUserUsername())")
	public ResponseEntity<OfferDto> save(@RequestBody OfferDto offer) throws UnavailableException, ElementNotFoundException, NoFundException{
		return ResponseEntity.ok(offerService.save(offer));
	}
	
	@DeleteMapping("/{username}/{id}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public void delete(@PathVariable("username") String username, @PathVariable("id") Long id) throws ElementNotFoundException {
		offerService.delete(id, username);
	}
	
	@GetMapping("/personal/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public ResponseEntity<List<OfferDto>> getPersonalOffers(@PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(offerService.getPersonalOffers(username));
	}
	
	@PostMapping("/accept/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#offer.getPostSellerUsername())")
	public void acceptOffer(@RequestBody OfferDto offer, @PathVariable("username") String username) throws ElementNotFoundException {
		offerService.acceptOffer(offer, username);
	}
	
	@PostMapping("/deny/{username}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#offer.getPostSellerUsername())")
	public void debyOffer(@RequestBody OfferDto offer, @PathVariable("username") String username) throws ElementNotFoundException {
		offerService.denyOffer(offer, username);
	}

}
