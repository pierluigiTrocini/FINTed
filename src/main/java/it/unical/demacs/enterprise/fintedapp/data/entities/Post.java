package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import it.unical.demacs.enterprise.fintedapp.handler.DateManager;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "post")
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User seller;
	
	@Column(name = "published_date")
	private Date publishedDate;
	
	@Column(name = "starting_price")
	private Long startingPrice;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "post_image")
	private String postImage;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = false)
	private List<Offer> offers;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private PostStatus status = PostStatus.AVAILABLE;
}
