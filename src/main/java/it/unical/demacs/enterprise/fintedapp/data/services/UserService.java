package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.keycloak.representations.AccessTokenResponse;

import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;

public interface UserService {
	
	void delete(String username) throws ElementNotFoundException;
	
	AccessTokenResponse save(UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException, MalformedURLException, IOException;
	
	UserPersonalProfileDto save1(UserRegistrationDto user) throws CredentialsAlreadyUsedException;
	
	List<UserProfileDto> getAll(Integer page);
	
	UserProfileDto get(String username) throws ElementNotFoundException, NullFieldException;
	
	UserPersonalProfileDto getPersonalProfile(String username) throws ElementNotFoundException, NullFieldException;
	
	UserPersonalProfileDto update(UserPersonalProfileDto user) throws ElementNotFoundException, NullFieldException;
	
}
