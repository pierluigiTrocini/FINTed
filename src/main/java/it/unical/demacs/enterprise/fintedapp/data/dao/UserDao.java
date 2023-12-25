package it.unical.demacs.enterprise.fintedapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.unical.demacs.enterprise.fintedapp.data.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	boolean existsByCredentialsEmail(String email);
	
	boolean existsByUsername(String username);
}
