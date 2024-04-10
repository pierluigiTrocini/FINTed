package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NoFundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import jakarta.servlet.UnavailableException;

public interface OfferService {
	
	OfferDto save(OfferDto offer) throws ElementNotFoundException, UnavailableException, NoFundException;
	
	void delete(Long id, String username) throws ElementNotFoundException;
	
	List<OfferDto> getPersonalOffers(String username) throws ElementNotFoundException;
	
	SpeditionDto acceptOffer(OfferDto offer, String sellerUsername) throws ElementNotFoundException;
	
	void denyOffer(OfferDto offer, String sellerUsername) throws ElementNotFoundException;
}
