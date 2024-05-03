package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;


@Repository
public interface PostDao extends JpaRepository<Post, Long> {
	
	List<Post> findAllBySellerIdNot(Long sellerId, PageRequest pageRequest);
	
	List<Post> findAllByTitleLike(String title);

	List<Post> findAllBySellerId(Long id);

	List<Post> findAllBySeller(User user);
	
	@Query("SELECT p.postImage FROM Post p WHERE p.id = :id")
	String findPostImageById(@Param("id") Long postId);
	
}
