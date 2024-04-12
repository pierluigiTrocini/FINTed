package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.OfferStatus;
import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import jakarta.transaction.Transactional;

@Repository
public interface OfferDao extends JpaRepository<Offer, Long> {
	List<Offer> findAllByPost(Post post);

	List<Offer> findAllByUser(User user);

	List<Offer> findAllByPostSellerIdAndOfferStatus(Long sellerId, OfferStatus status);
	
	List<Offer> findAllByPostSeller(User seller);

	Boolean existsByUserIdAndPostId(Long userId, Long postId);

	@Transactional
	@Modifying
	@Query("UPDATE Offer o SET o.offerStatus = :status WHERE o.post.id = :postId AND o.id != :offerId")
	void setStatus(@Param("postId") Long postId, @Param("status") OfferStatus status, @Param("offerId") Long offerId);
//	
//	@Query("UPDATE Offer o SET o.offerStatus = :status WHERE o.post.id = :id AND o.offerStatus != ACCEPTED")
//	void postDeleted(@Param("status") OfferStatus status, @Param("id") Long postId);
	
}
