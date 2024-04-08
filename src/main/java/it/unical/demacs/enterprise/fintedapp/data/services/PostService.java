package it.unical.demacs.enterprise.fintedapp.data.services;


import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.PostDto;

public interface PostService {

	PostDto save(PostDto post);
	
	void delete(Long postId);
	
	PostDto update(PostDto post);
	
	PostDto get(Long postId);
	
	List<PostDto> getByUser(String username);
	
	List<PostDto> getAll(Integer page);
}
