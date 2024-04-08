package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import org.keycloak.representations.AccessTokenResponse;

import it.unical.demacs.enterprise.fintedapp.dto.UserDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;

public interface UserService {
	
	AccessTokenResponse save(UserRegistrationDto user);
	
	void delete(String username);
	
	UserPersonalProfileDto update(UserRegistrationDto user);
	
	UserProfileDto get(String username);
	
	UserPersonalProfileDto getPersonal(String username);
	
	List<UserDto> searchByUsername(String username);
	
	List<UserDto> getAll(Integer page);
	
}
