package it.unical.demacs.enterprise.fintedapp.data.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Spedition;
import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.dto.SpeditionDto;

@Repository
public interface SpeditionDao extends JpaRepository<Spedition, Long>{
	
	List<Spedition> findAllByPurchaser(User purchaser);

	List<SpeditionDto> findAllBySellerUsername(String username);
	
}
