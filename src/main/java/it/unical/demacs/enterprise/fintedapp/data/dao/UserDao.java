package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import jakarta.transaction.Transactional;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	boolean existsByCredentialsEmail(String email);
	
	boolean existsByUsername(String username);
	
	List<User> findByUsernameLike(String username);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE User u SET u.balance = u.balance + (SELECT o.offer FROM Offer o WHERE o.post.id = :postId AND o.offerStatus = 'POST_DELETED') WHERE u.id IN (SELECT o.user.id FROM Offer o WHERE o.post.id = :postId AND o.offerStatus = 'POST_DELETED')")
	void refundAll(@Param("postId") Long postId);

	Optional<User> findByUsername(String orElseThrow);
}
