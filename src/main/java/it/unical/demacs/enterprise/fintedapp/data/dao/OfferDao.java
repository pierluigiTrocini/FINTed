package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;

@Repository
public interface OfferDao extends JpaRepository<Offer, Long>{	
	List<Offer> findAllByPost(Post post);
	
	List<Offer> findAllByUser(User user);
	
	List<Offer> findAllByPostSellerId(Long sellerId);
}
