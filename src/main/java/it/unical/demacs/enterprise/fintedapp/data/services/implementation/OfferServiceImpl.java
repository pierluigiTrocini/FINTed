package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.OfferDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.PostDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.OfferStatus;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.OfferService;
import it.unical.demacs.enterprise.fintedapp.data.services.SpeditionService;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NoFundException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

	private final OfferDao offerDao;
	private final UserDao userDao;
	private final PostDao postDao;
	
	private final SpeditionService speditionService;
	
	private final ModelMapper modelMapper;
	
	@Override
	public OfferDto save(OfferDto offer) throws ElementNotFoundException, UnavailableException, NoFundException {
		User user = userDao.findByUsername(offer.getUserUsername()).orElseThrow(() -> new ElementNotFoundException("post not found"));
		if(user.getBalance() < offer.getOffer())
			throw new NoFundException("not enough fund");
		
		Offer newOffer = new Offer();
		
		newOffer.setPost(postDao.findById(offer.getPostId()).orElseThrow(() -> new ElementNotFoundException("post not found")));
		newOffer.setUser(user);
		newOffer.setPublishDate(DateManager.getInstance().currentDate());
		newOffer.setOffer(offer.getOffer());
		newOffer.setOfferStatus(OfferStatus.PENDING);
		
		user.setBalance(user.getBalance() - offer.getOffer());
		userDao.save(user);
		
		return modelMapper.map(offerDao.save(newOffer), OfferDto.class);
	}

	@Override
	public void delete(Long id, String username) throws ElementNotFoundException {
		Offer offer = offerDao.findById(id).orElseThrow(() -> new ElementNotFoundException("post not found"));
		if(!offer.getUser().getUsername().equals(username))
			throw new AuthorizationDeniedException("unauthorized", null);
		offerDao.delete(offer);
	}

	@Override
	public List<OfferDto> getPersonalOffers(String username) throws ElementNotFoundException {
		return offerDao.findAllByUser(userDao.findByUsername(username).orElseThrow(() -> new ElementNotFoundException("user not found")))
				.stream().map(offer -> modelMapper.map(offer, OfferDto.class)).collect(Collectors.toList());
	}

	@Override
	public SpeditionDto acceptOffer(OfferDto offer, String sellerUsername) throws ElementNotFoundException {
		Offer o = offerDao.findById(offer.getId()).orElseThrow(() -> new ElementNotFoundException("offer not found"));	
		
		if(!o.getPost().getSeller().getUsername().equals(sellerUsername))
			throw new AuthorizationDeniedException("unauthorized", null);

		o.setOfferStatus(OfferStatus.ACCEPTED);
		offerDao.save(o);
		offerDao.setStatus(o.getPost().getId(), OfferStatus.DENIED, o.getId());
		
		return speditionService.save(o);
	}

	@Override
	public void denyOffer(OfferDto offer, String sellerUsername) throws ElementNotFoundException {
		Offer o = offerDao.findById(offer.getId()).orElseThrow(() -> new ElementNotFoundException("offer not found"));
		
		if(!o.getPost().getSeller().getUsername().equals(sellerUsername))
			throw new AuthorizationDeniedException("unauthorized", null);
		
		o.setOfferStatus(OfferStatus.DENIED);		
		offerDao.save(o);
	}

}











