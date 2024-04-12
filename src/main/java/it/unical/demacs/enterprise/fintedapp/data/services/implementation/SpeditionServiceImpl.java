package it.unical.demacs.enterprise.fintedapp.data.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import it.unical.demacs.enterprise.fintedapp.data.dao.SpeditionDao;
import it.unical.demacs.enterprise.fintedapp.data.dao.UserDao;
import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;
import it.unical.demacs.enterprise.fintedapp.data.entities.Spedition;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.data.services.SpeditionService;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;
import it.unical.demacs.enterprise.fintedapp.exception.ElementNotFoundException;
import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeditionServiceImpl implements SpeditionService {

	private final SpeditionDao speditionDao;
	private final UserDao userDao;
	
	private final ModelMapper modelMapper;
	
	@Override
	public SpeditionDto save(Offer offer) throws ElementNotFoundException {
		Spedition newSpedition = new Spedition();
		
		newSpedition.setOffer(offer);
		newSpedition.setSeller(offer.getPost().getSeller());
		newSpedition.setPurchaser(offer.getUser());
		newSpedition.setDate(DateManager.getInstance().currentDate());
		
		
		return modelMapper.map(speditionDao.save(newSpedition), SpeditionDto.class);
	}

	@Override
	public List<SpeditionDto> getBySeller(String username) {
		return speditionDao.findAllBySellerUsername(username).stream()
				.map(sped -> modelMapper.map(sped, SpeditionDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<SpeditionDto> getByPurchaser(User purchaser) {
		return speditionDao.findAllByPurchaser(purchaser).stream()
				.map(sped -> modelMapper.map(sped, SpeditionDto.class)).collect(Collectors.toList());
	}

}
