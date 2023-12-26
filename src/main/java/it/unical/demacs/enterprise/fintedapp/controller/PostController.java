package it.unical.demacs.enterprise.fintedapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.PostService;
import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.CredentialsAlreadyUsedException;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@PostMapping("/")
	public ResponseEntity<PostDto> save(@RequestBody PostDto post) throws CredentialsAlreadyUsedException, NullFieldException{
		return ResponseEntity.ok(postService.save(post));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Long id) {
		postService.delete(id);
	}
	
	@GetMapping("/{page}")
	public ResponseEntity<List<PostDto>> getAll(@PathVariable("page") Integer page){
		return ResponseEntity.ok(postService.getAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> get(@PathVariable("id") Long id) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(postService.get(id));
	}
	
	@PutMapping("/")
	public ResponseEntity<PostDto> update(@RequestBody PostDto post) throws ElementNotFoundException, NullFieldException{
		return ResponseEntity.ok(postService.update(post));
	}
}
