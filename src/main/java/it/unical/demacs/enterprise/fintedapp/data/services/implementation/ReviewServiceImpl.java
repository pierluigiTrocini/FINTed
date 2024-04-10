package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.ReviewDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Review;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.ReviewService;
import it.unical.demacs.enterprise.fintedapp.dto.ReviewDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

	private final ReviewDao reviewDao;
	private final UserDao userDao;
	
	private final ModelMapper modelMapper;
	
	@Override
	public ReviewDto save(ReviewDto review) throws ElementNotFoundException {
		User author = userDao.findByUsername(review.getAuthorUsername()).orElseThrow(() -> new ElementNotFoundException("user [author] not found"));
		User target = userDao.findByUsername(review.getTargetUsername()).orElseThrow(() -> new ElementNotFoundException("user [target] not found"));
		
		Review newReview = new Review();
		newReview.setAuthor(author);
		newReview.setTarget(target);
		newReview.setPublishedDate(DateManager.getInstance().currentDate());
		newReview.setContent(review.getContent().trim());
		
		return modelMapper.map(reviewDao.save(newReview), ReviewDto.class);		
	}

	@Override
	public void delete(Long id, String username) throws ElementNotFoundException {
		Review review = reviewDao.findById(id).orElseThrow(() -> new ElementNotFoundException("review not found"));
		if(!review.getAuthor().getUsername().equals(username))
			throw new AuthorizationDeniedException("unauthorized", null);

		reviewDao.delete(review);
	}

	@Override
	public List<ReviewDto> getByTarget(String targetUsername) throws ElementNotFoundException {
		return reviewDao.findAllByTarget(
				userDao.findByUsername(targetUsername).orElseThrow(() -> new ElementNotFoundException("user [target] not found"))
				).stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ReviewDto> getPersonal(String username) throws ElementNotFoundException {
		return reviewDao.findAllByAuthor(
				userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user [user] not found"))
				).stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
	}

}
