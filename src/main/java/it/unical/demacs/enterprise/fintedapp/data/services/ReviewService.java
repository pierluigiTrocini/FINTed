package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.ReviewDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;

public interface ReviewService {
    ReviewDto save(ReviewDto review) throws ElementNotFoundException;

    void delete(Long id);

    List<ReviewDto> getByTarget(String targetUsername);
    
    List<ReviewDto> getPersonal(String username);
}
