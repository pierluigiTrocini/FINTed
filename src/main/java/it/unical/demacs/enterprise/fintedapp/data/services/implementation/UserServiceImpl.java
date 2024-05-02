package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.representations.AccessTokenResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.KeycloakService;
import it.unical.demacs.enterprise.fintedapp.data.services.UserService;
import it.unical.demacs.enterprise.fintedapp.dto.UserDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final KeycloakService keycloakService;

	private final UserDao userDao;

	private final ModelMapper modelMapper;

	@Override
	public AccessTokenResponse save(UserRegistrationDto user)
			throws CredentialsAlreadyUsedException, MalformedURLException, IOException {
		if (userDao.existsByCredentialsEmail(user.getCredentialsEmail()))
			throw new CredentialsAlreadyUsedException("email already used");
		if (userDao.existsByUsername(user.getUsername()))
			throw new CredentialsAlreadyUsedException("username already used");

		User newUser = modelMapper.map(user, User.class);
		newUser.getCredentials().setPassword(BCrypt.hashpw(user.getCredentialsPassword(), BCrypt.gensalt(12)));
		newUser.setRegistrationDate(DateManager.getInstance().currentDate());

		newUser = userDao.save(newUser);
		try {
			keycloakService.createKeycloakUser(newUser, user.getCredentialsPassword());
		} catch (Exception e) {
			userDao.delete(newUser);
		}

		return keycloakService.login(newUser.getUsername(), user.getCredentialsPassword());
	}

	@Override
	public void delete(String username) throws ElementNotFoundException {
		User user = userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user not found"));
		userDao.delete(user);
		keycloakService.deleteUser(username);
	}

	@Override
	public UserPersonalProfileDto update(UserPersonalProfileDto user) throws ElementNotFoundException {
		User _user = userDao.findByUsername(user.getUsername())
				.orElseThrow(() -> new ElementNotFoundException("user not found"));

		_user.getAddress().setRoute(user.getAddressRoute());
		_user.getAddress().setNumber(user.getAddressNumber());
		_user.getAddress().setCity(user.getAddressCity());

		return modelMapper.map(userDao.save(_user), UserPersonalProfileDto.class);
	}

	@Override
	public UserProfileDto get(String username) throws ElementNotFoundException {
		return modelMapper.map(
				userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user not found")),
				UserProfileDto.class);
	}

	@Override
	public UserPersonalProfileDto getPersonal(String username) throws ElementNotFoundException {
		return modelMapper.map(
				userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user not found")),
				UserPersonalProfileDto.class);
	}

	@Override
	public List<UserDto> searchByUsername(String username) {
		return userDao.findByUsernameLike(username).stream()
				.map(user -> modelMapper.map(user, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<UserDto> getAll(Integer page) {
		return userDao.findAll(PageRequest.of((page != null) ? page : 0, 10)).stream()
				.map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}
}
