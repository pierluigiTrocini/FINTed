package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;

public interface PostService {

	PostDto save(PostDto post);
	
	void delete(Long id);
	
	List<PostDto> getAll(Integer page);
	
	PostDto get(Long id) throws ElementNotFoundException, NullFieldException;
	
	PostDto update(PostDto post) throws ElementNotFoundException, NullFieldException;
	
}
