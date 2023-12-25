package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Review;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;

@Repository
public interface ReviewDao extends JpaRepository<Review, Long>{
	List<Review> findAllByTarget(User target);
	
	List<Review> findAllByAuthor(User author);

}
