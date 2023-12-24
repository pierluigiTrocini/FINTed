package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;

public interface OfferService {
	
	OfferDto save(OfferDto offer);
	
	void delete(Long id);
	
    List<OfferDto> getPostOffers(Long postId);

    List<OfferDto> getUserOffers(Long userId);
	
}
