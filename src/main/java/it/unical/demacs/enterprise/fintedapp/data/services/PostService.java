package it.unical.demacs.enterprise.fintedapp.data.services;

import java.io.IOException;
import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;

public interface PostService {

	PostDto save(PostDto post) throws ElementNotFoundException, IOException;
	
	void delete(Long id, String username) throws ElementNotFoundException;
	
	List<PostDto> getAll(Integer page) throws Exception;
	
	PostDto get(Long id) throws ElementNotFoundException, NullFieldException;
	
	PostDto update(PostDto post) throws ElementNotFoundException, NullFieldException;

	List<PostDto> getHomepage(Integer page, Long userId);
	
}
