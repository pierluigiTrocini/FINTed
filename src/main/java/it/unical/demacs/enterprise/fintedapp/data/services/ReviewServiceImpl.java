package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.ReviewDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Review;
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
		if(!userDao.existsById(review.getAuthor().getId()))
			throw new ElementNotFoundException("User [author] not found");
		if(!userDao.existsById(review.getTarget().getId()))
			throw new ElementNotFoundException("User [user] not found");
		
		Review newReview = modelMapper.map(review, Review.class);
		newReview.setPublishedDate(DateManager.getInstance().currentDateSQLFormat());
		
		return modelMapper.map(reviewDao.save(newReview), ReviewDto.class);		
	}

	@Override
	public void delete(Long id) {
		reviewDao.deleteById(id);
	}

	@Override
	public List<ReviewDto> getTargetReviews(Long targetId) throws ElementNotFoundException {
        return reviewDao.findAllByTarget(
                userDao.findById(targetId).orElseThrow(() -> new ElementNotFoundException("user not found"))
            ).stream()
            .map(review -> modelMapper.map(review, ReviewDto.class))
            .collect(Collectors.toList());
	}

	@Override
	public List<ReviewDto> getAuthorReviews(Long authorId) throws ElementNotFoundException {
        return reviewDao.findAllByAuthor(
                userDao.findById(authorId).orElseThrow(() -> new ElementNotFoundException("user not found"))
            ).stream()
            .map(review -> modelMapper.map(review, ReviewDto.class))
            .collect(Collectors.toList());
	}
	
}
