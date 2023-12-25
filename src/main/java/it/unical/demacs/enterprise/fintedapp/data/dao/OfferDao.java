package it.unical.demacs.enterprise.fintedapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Offer;

@Repository
public interface OfferDao extends JpaRepository<Offer, Long>{

}
