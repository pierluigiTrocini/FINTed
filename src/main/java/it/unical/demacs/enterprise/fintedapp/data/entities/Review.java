package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User target;
	
	@Column(name = "published_date")
	private Date publishedDate;
	
	@Column
	private String content;
}
