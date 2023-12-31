package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.PostDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.dto.PostDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostDao postDao;
	private final UserDao userDao;
	
	private final ModelMapper modelMapper;

	@Override
	public PostDto save(PostDto post) throws ElementNotFoundException {
		if(!userDao.existsById(post.getSeller().getId()))
			throw new ElementNotFoundException("User [seller] not found");
		
		Post newPost =  modelMapper.map(postDao.save(modelMapper.map(post, Post.class)), Post.class);
		newPost.setPublishedDate(DateManager.getInstance().currentDateSQLFormat());
		
		return modelMapper.map(postDao.save(newPost), PostDto.class);
	}

	@Override
	public void delete(Long id) {
		postDao.deleteById(id);
	}

	@Override
	public List<PostDto> getAll(Integer page) {
		return postDao.findAll(PageRequest.of(page != null && page >= 0 ? page : 0, 10)).stream()
				.map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

	}

	@Override
	public PostDto get(Long id) throws ElementNotFoundException, NullFieldException {
        return modelMapper.map(
                postDao.findById(Optional.ofNullable(id).orElseThrow(() -> new NullFieldException("no id as request param")))
                    .orElseThrow(() -> new ElementNotFoundException("user not found")),
                PostDto.class
            );
	}

	@Override
	public PostDto update(PostDto post) throws ElementNotFoundException, NullFieldException {
        Post _post = postDao.findById(Optional.ofNullable(post.getId()).orElseThrow(() -> new NullFieldException("no id value")))
                .orElseThrow(() -> new ElementNotFoundException("post not found"));

            _post.setTitle(post.getTitle());
            _post.setStartingPrice(post.getStartingPrice());

            return modelMapper.map(postDao.save(_post), PostDto.class);
	}

}
