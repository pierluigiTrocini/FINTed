package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;

public interface OfferService {
	
	OfferDto save(OfferDto offer) throws ElementNotFoundException;
	
	void delete(Long id);
	
    List<OfferDto> getPostOffers(Long postId) throws ElementNotFoundException, NullFieldException;

    List<OfferDto> getUserOffers(Long userId) throws ElementNotFoundException, NullFieldException;
	
}
