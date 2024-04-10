package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;

public interface SpeditionService {

	SpeditionDto save(Offer offer) throws ElementNotFoundException;
	
	List<SpeditionDto> getBySeller(String username);
	
	List<SpeditionDto> getByPurchaser(User purchaser);
	
}
