package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.util.Date;

import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "offer")
@NoArgsConstructor
public class Offer {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@Column(name = "publish_date")
	private Date publishDate;
	
	@Column
	private Long offer;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private OfferStatus offerStatus = OfferStatus.PENDING;
}
