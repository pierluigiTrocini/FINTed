package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;

public interface UserService {
	
	void delete(Long id);
	
	UserProfileDto save(UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException;
	
	List<UserProfileDto> getAll(Integer page);
	
	UserProfileDto get(long id) throws ElementNotFoundException, NullFieldException;
	
	UserPersonalProfileDto getPersonalProfile(Long id) throws ElementNotFoundException, NullFieldException;
	
	UserPersonalProfileDto update(UserPersonalProfileDto user) throws ElementNotFoundException, NullFieldException;
	
}
