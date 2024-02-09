package it.unical.demacs.enterprise.fintedapp.data.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.OfferDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.PostDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.OfferStatus;
import it.unical.demacs.enterprise.fintedapp.data.entities.Post;
import it.unical.demacs.enterprise.fintedapp.data.entities.PostStatus;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NoFundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
	private final OfferDao offerDao;
	private final UserDao userDao;
	private final PostDao postDao;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public OfferDto save(OfferDto offer) throws ElementNotFoundException, UnavailableException, NoFundException {
		Post post = postDao.findById(offer.getPostId()).orElseThrow(() -> new ElementNotFoundException("Post not found"));
		User user = userDao.findById(offer.getUserId()).orElseThrow(() -> new ElementNotFoundException("User [user] not found"));
		
		if(offerDao.existsByUserIdAndPostId(offer.getUserId(), offer.getPostId()))
			throw new UnavailableException("You have already sent an offer for this item");
		if(user.getBalance() < offer.getOffer())
			throw new NoFundException("Not enough fund for this operation");
		
		Offer newOffer = modelMapper.map(offer, Offer.class);
		
		newOffer.setPost(post);
		newOffer.setUser(user);
		newOffer.setPublishDate(DateManager.getInstance().currentDateSQLFormat());
		newOffer.setOfferStatus(OfferStatus.PENDING);
		
		user.setBalance(user.getBalance() - offer.getOffer());
		userDao.save(user);
		
		return modelMapper.map(offerDao.save(newOffer), OfferDto.class);
	}

	@Override
	public void delete(Long id) {
		offerDao.deleteById(id);
	}

	@Override
	public List<OfferDto> getPostOffers(Long postId) throws ElementNotFoundException, NullFieldException {
		return offerDao.findAllByPost(
				postDao.findById(Optional.ofNullable(postId).orElseThrow(() -> new NullFieldException("No id [post-id] as request param")))
				.orElseThrow(() -> new ElementNotFoundException("Post not found"))
		).stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<OfferDto> getUserOffers(Long userId) throws ElementNotFoundException, NullFieldException {
		return offerDao.findAllByUser(
				userDao.findById(Optional.ofNullable(userId).orElseThrow(() -> new NullFieldException("No id [user-id] as request param")))
				.orElseThrow(() -> new ElementNotFoundException("User not found"))
		).stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<OfferDto> getSellOffers(Long id) {
		return offerDao.findAllByPostSellerIdAndOfferStatus(id, OfferStatus.PENDING).stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());	
	}

	@Override
	public OfferDto acceptOffer(OfferDto offer) throws ElementNotFoundException {
		User seller = userDao.findById(offer.getPostSellerId()).orElseThrow(() -> new ElementNotFoundException("User [seller] not found"));
		Post post = postDao.findById(offer.getPostId()).orElseThrow(() -> new ElementNotFoundException("Post not found"));
		Offer _offer = offerDao.findById(offer.getId()).orElseThrow(() -> new ElementNotFoundException("Offer not found"));
		
		
		seller.setBalance(seller.getBalance() + _offer.getOffer());
		userDao.save(seller);
		
		post.setStatus(PostStatus.UNAVAILABLE);
		postDao.save(post);
		
		_offer.setOfferStatus(OfferStatus.ACCEPTED);
		
		offerDao.setStatus(offer.getPostId(), OfferStatus.UNAVAILABLE, offer.getId());
		
		return modelMapper.map(offerDao.save(_offer), OfferDto.class);
	}

	@Override
	public OfferDto denyOffer(OfferDto offer) throws ElementNotFoundException, UnavailableException {
		User user = userDao.findById(offer.getUserId()).orElseThrow(() -> new ElementNotFoundException("User [user] not found"));
		if(!postDao.existsById(offer.getPostId()))
			throw new ElementNotFoundException("Post not found");
		Offer _offer = offerDao.findById(offer.getId()).orElseThrow(() -> new ElementNotFoundException("Post not found"));
		
		if(offer.getOfferStatus() == OfferStatus.UNAVAILABLE )
			throw new UnavailableException("Unavailable operation");
		
		user.setBalance(user.getBalance() + offer.getOffer());
		_offer.setOfferStatus(OfferStatus.DENIED);
		
		return modelMapper.map(offerDao.save(_offer), OfferDto.class);
	}

}
