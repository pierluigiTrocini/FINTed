package it.unical.demacs.enterprise.fintedapp.data.services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.OfferDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.PostDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.exception.NullFieldException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {
	private final OfferDao offerDao;
	private final UserDao userDao;
	private final PostDao postDao;
	
	private final ModelMapper modelMapper;
	
	
	@Override
	public OfferDto save(OfferDto offer) throws ElementNotFoundException {
		if(!postDao.existsById(offer.getPost().getId()))
			throw new ElementNotFoundException("Post not found");
		if(!userDao.existsById(offer.getUser().getId()))
			throw new ElementNotFoundException("User not found");
		
		Offer newOffer = modelMapper.map(offer, Offer.class);
		newOffer.setPublishDate((Date) DateManager.getInstance().currentDate());
		
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

}
