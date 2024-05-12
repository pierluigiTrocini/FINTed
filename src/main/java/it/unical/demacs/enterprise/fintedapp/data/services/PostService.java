package it.unical.demacs.enterprise.fintedapp.data.services;


import java.io.IOException;
import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;

public interface PostService {

	PostDto save(PostDto post) throws ElementNotFoundException, IOException;
	
	void delete(Long postId, String username) throws ElementNotFoundException;
	
	PostDto get(Long postId) throws ElementNotFoundException;
	
	List<PostDto> getByUser(String username) throws ElementNotFoundException;
	
	List<PostDto> getAll(Integer page);

    List<PostDto> searchByTitle(String title);
    
    List<PostDto> searchBySellerUsername(String username);
    
    String getImage(Long postId) throws IOException;
    
}
