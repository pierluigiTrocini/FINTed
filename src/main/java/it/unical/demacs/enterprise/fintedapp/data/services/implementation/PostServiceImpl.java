package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.PostDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.data.entities.PostStatus;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.ImageService;
import it.unical.demacs.enterprise.fintedapp.data.services.PostService;
import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	
	private final PostDao postDao;
	private final UserDao userDao;
	private final ImageService imageService;
	
	private final ModelMapper modelMapper;
	
	@Override
	public PostDto save(PostDto post) throws ElementNotFoundException, IOException {
		Post newPost = new Post();
		newPost.setTitle(post.getTitle().trim());
		newPost.setSeller(userDao.findByUsername(post.getSellerUsername()).orElseThrow(() -> new ElementNotFoundException("user not found")));
		newPost.setPublishedDate(DateManager.getInstance().currentDate());
		newPost.setStartingPrice(post.getStartingPrice());
		newPost.setStatus(PostStatus.AVAILABLE);
		
		newPost.setPostImage(imageService.compress(post.getPostImage()));
		
		return modelMapper.map(postDao.save(newPost), PostDto.class);
	}

	@Override
	public void delete(Long postId, String username) throws ElementNotFoundException {
		Post post = postDao.findById(postId).orElseThrow(() -> new ElementNotFoundException("post not found"));
		if(!post.getSeller().getUsername().equals(username))
			throw new AuthorizationDeniedException("unauthorized", null);
		
		postDao.delete(post);
	}
	
	@Override
	public List<PostDto> searchByTitle(String title){
		return postDao.findAllByTitleLike(title).stream()
				.map(post -> {
					PostDto postDto = modelMapper.map(post, PostDto.class);
					
					try {
						postDto.setPostImage(imageService.decompress(postDto.getPostImage()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return postDto;
				}).collect(Collectors.toList());
	}

	@Override
	public PostDto get(Long postId) throws ElementNotFoundException {
		return modelMapper.map(postDao.findById(postId).orElseThrow(() -> new ElementNotFoundException("post not found")), PostDto.class);
	}

	@Override
	public List<PostDto> getByUser(String username) throws ElementNotFoundException {
		User user = userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user not found"));
		return user.getPublishedPosts().stream().map(post -> {
			PostDto postDto = modelMapper.map(post, PostDto.class);
			
			try {
				postDto.setPostImage(imageService.decompress(postDto.getPostImage()));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return postDto;
		}).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAll(Integer page) {
		return postDao.findAll(PageRequest.of((page != null) ? page : 0, 10)).stream()
				.map(post -> {
					PostDto postDto = modelMapper.map(post, PostDto.class);
					
					try {
						postDto.setPostImage(imageService.decompress(postDto.getPostImage()));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return postDto;
				}).collect(Collectors.toList());
	}

	@Override
	public String getImage(Long postId) throws IOException {
		return imageService.decompress(postDao.findPostImageById(postId));
	}

}
