package it.unical.demacs.enterprise.fintedapp.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import it.unical.demacs.enterprise.fintedapp.data.entities.Post;

public interface PostDao extends JpaRepository<Post, Long> {

}
