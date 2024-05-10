package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.keycloak.representations.AccessTokenResponse;

import it.unical.demacs.enterprise.fintedapp.dto.UserDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.InvalidArgumentException;

public interface UserService {
	
	AccessTokenResponse save(UserRegistrationDto user) throws CredentialsAlreadyUsedException, MalformedURLException, IOException;
	
	void delete(String username) throws ElementNotFoundException;
	
	UserPersonalProfileDto update(UserPersonalProfileDto user) throws ElementNotFoundException;
	
	UserProfileDto get(String username) throws ElementNotFoundException;
	
	UserPersonalProfileDto getPersonal(String username) throws ElementNotFoundException;
	
	List<UserDto> searchByUsername(String username);
	
	List<UserDto> getAll(Integer page);	
}
