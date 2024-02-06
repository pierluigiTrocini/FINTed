package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Long> {

	List<Post> findAllBySellerIdNot(Long sellerId, PageRequest pageRequest);
	
}
