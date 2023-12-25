package it.unical.demacs.enterprise.fintedapp.data.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	@Override
	public void delete(Long id) {
		userDao.deleteById(id);
	}

	@Override
	public UserProfileDto save(UserRegistrationDto user) throws CredentialsAlreadyUsedException, NullFieldException {
		if (userDao.existsByCredentialsEmail(user.getCredentialsEmail().toString()))
			throw new CredentialsAlreadyUsedException("Email already used");
		if (userDao.existsByUsername(user.getUsername().toString()))
			throw new CredentialsAlreadyUsedException("Username already used");

		User newUser = modelMapper.map(user, User.class);

		newUser.getCredentials().setPassword(BCrypt.hashpw(user.getCredentialsPassword(), "12"));
		newUser.setRegistrationDate((Date) DateManager.getInstance().currentDate());
		newUser.setBalance((long) 500);

		return modelMapper.map(userDao.save(newUser), UserProfileDto.class);
	}

	@Override
	public List<UserProfileDto> getAll(Integer page) {
		return userDao.findAll(PageRequest.of(page != null && page >= 0 ? page : 0, 10)).stream()
				.map(user -> modelMapper.map(user, UserProfileDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserProfileDto get(long id) throws ElementNotFoundException, NullFieldException {
		return modelMapper.map(userDao
				.findById(Optional.ofNullable(id).orElseThrow(() -> new NullFieldException("no id as request param")))
				.orElseThrow(() -> new ElementNotFoundException("user not found")), UserProfileDto.class);
	}

	@Override
	public UserPersonalProfileDto getPersonalProfile(Long id) throws ElementNotFoundException, NullFieldException {
		return modelMapper.map(userDao
				.findById(Optional.ofNullable(id).orElseThrow(() -> new NullFieldException("no id as request param")))
				.orElseThrow(() -> new ElementNotFoundException("user not found")), UserPersonalProfileDto.class);
	}

	@Override
	public UserPersonalProfileDto update(UserPersonalProfileDto user) throws ElementNotFoundException, NullFieldException {
	    User _user = userDao.findById(Optional.ofNullable(user.getId()).orElseThrow(() -> new NullFieldException("no id in request body"))).orElseThrow(() -> new ElementNotFoundException("user not found"));
	
	    _user.getCredentials().setEmail(user.getCredentialsEmail());
	    
	    _user.getAddress().setCity(user.getAddressCity());
	    _user.getAddress().setNumber(user.getAddressCity());
	    _user.getAddress().setRoute(user.getAddressCity());        
	
	    return modelMapper.map(userDao.save(_user), UserPersonalProfileDto.class);
	}

}
