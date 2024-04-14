package it.unical.demacs.enterprise.fintedapp.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.enterprise.fintedapp.data.services.PostService;
import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@PostMapping("/")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#post.getSellerUsername())")
	public ResponseEntity<PostDto> save(@RequestBody PostDto post, @RequestHeader(value="Authorization") String token) throws ElementNotFoundException, IOException{
		return ResponseEntity.ok(postService.save(post));
	}
	
	@DeleteMapping("/{username}/{postId}")
	@PreAuthorize("authentication.principal.claims['preferred_username'].equals(#username)")
	public void delete(@PathVariable("postId") Long postId, @PathVariable("username") String username, @RequestHeader(value="Authorization") String token) throws ElementNotFoundException {
		postService.delete(postId, username);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> get(@PathVariable("postId") Long postId) throws ElementNotFoundException{
		return ResponseEntity.ok(postService.get(postId));
	} 
	
	@GetMapping("/user/{username}")
	public ResponseEntity<List<PostDto>> getByUser(@PathVariable("username") String username) throws ElementNotFoundException{
		return ResponseEntity.ok(postService.getByUser(username));
	}
	
	@GetMapping("/all/{page}")
	public ResponseEntity<List<PostDto>> getAll(@PathVariable("page") Integer page) throws ElementNotFoundException{
		return ResponseEntity.ok(postService.getAll(page));
	}
	
	@GetMapping("/search/{content}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("content") String content){
		return ResponseEntity.ok(postService.searchByTitle(content));
	}
}
