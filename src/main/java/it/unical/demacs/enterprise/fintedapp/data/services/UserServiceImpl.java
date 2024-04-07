package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.keycloak.representations.AccessTokenResponse;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserProfileDto;
import it.unical.demacs.enterprise.fintedapp.dto.UserRegistrationDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final ModelMapper modelMapper;
	private final UserDao userDao;
	
	private final KeycloakService keycloakService;

	@Override
	public void delete(String username) throws ElementNotFoundException {
		User user = userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("User not found"));
		userDao.deleteById(user.getId());
		keycloakService.deleteUser(user.getUsername());
	}
	
	@Override
	public UserPersonalProfileDto save1(UserRegistrationDto user) throws CredentialsAlreadyUsedException {
		if (userDao.existsByCredentialsEmail(user.getCredentialsEmail().toString()))
			throw new CredentialsAlreadyUsedException("Email already used");
		if (userDao.existsByUsername(user.getUsername().toString()))
			throw new CredentialsAlreadyUsedException("Username already used");
		
		User newUser = modelMapper.map(user, User.class);
		
		newUser.getCredentials().setPassword(BCrypt.hashpw(user.getCredentialsPassword(), BCrypt.gensalt(12)));
		newUser.setRegistrationDate(DateManager.getInstance().currentDateSQLFormat());
		newUser.setBalance((long) 500);
		
		return modelMapper.map(userDao.save(newUser), UserPersonalProfileDto.class);
	}

	@Override
	public AccessTokenResponse save(UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException, MalformedURLException, IOException {
		if (userDao.existsByCredentialsEmail(user.getCredentialsEmail().toString()))
			throw new CredentialsAlreadyUsedException("Email already used");
		if (userDao.existsByUsername(user.getUsername().toString()))
			throw new CredentialsAlreadyUsedException("Username already used");

		User newUser = modelMapper.map(user, User.class);

		newUser.getCredentials().setPassword(BCrypt.hashpw(user.getCredentialsPassword(), BCrypt.gensalt(12)));
		newUser.setRegistrationDate(DateManager.getInstance().currentDateSQLFormat());
		newUser.setBalance((long) 500);
		
		return keycloakService.createKeycloakUser(userDao.save(newUser), user.getCredentialsPassword());
	}

	@Override
	public List<UserProfileDto> getAll(Integer page) {
		return userDao.findAll(PageRequest.of(page != null && page >= 0 ? page : 0, 10)).stream()
				.map(user -> modelMapper.map(user, UserProfileDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserProfileDto get(String username) throws ElementNotFoundException, NullFieldException {
		return modelMapper.map(userDao
				.findByUsername(Optional.ofNullable(username).orElseThrow(() -> new NullFieldException("no id as request param")))
				.orElseThrow(() -> new ElementNotFoundException("user not found")), UserProfileDto.class);
	}

	@Override
	public UserPersonalProfileDto getPersonalProfile(String username)
			throws ElementNotFoundException, NullFieldException {
		return modelMapper
				.map(userDao
						.findByUsername(Optional.ofNullable(username)
								.orElseThrow(() -> new NullFieldException("no id as request param")))
						.orElseThrow(() -> new ElementNotFoundException("user not found")),
						UserPersonalProfileDto.class);
	}

	@Override
	public UserPersonalProfileDto update(UserPersonalProfileDto user)
			throws ElementNotFoundException, NullFieldException {
		User _user = userDao
				.findById(Optional.ofNullable(user.getId())
						.orElseThrow(() -> new NullFieldException("no id in request body")))
				.orElseThrow(() -> new ElementNotFoundException("user not found"));

		_user.getCredentials().setEmail(user.getCredentialsEmail());

		_user.getAddress().setCity(user.getAddressCity());
		_user.getAddress().setNumber(user.getAddressCity());
		_user.getAddress().setRoute(user.getAddressCity());

		return modelMapper.map(userDao.save(_user), UserPersonalProfileDto.class);
	}

}
