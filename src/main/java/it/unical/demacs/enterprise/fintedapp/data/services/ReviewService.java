package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;

import it.unical.demacs.enterprise.fintedapp.dto.ReviewDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;

public interface ReviewService {
    ReviewDto save(ReviewDto review) throws ElementNotFoundException;

    void delete(Long id, String username) throws ElementNotFoundException;

    List<ReviewDto> getByTarget(String targetUsername) throws ElementNotFoundException;
    
    List<ReviewDto> getPersonal(String username) throws ElementNotFoundException;
}
