package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "_user")
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(unique = true)
	private String username;
	
	@Column(name = "registration_date")
	private Date registrationDate;
	
	@Column(name = "account_disabled_date", nullable = true)
	private Date accountDisableDate = null;
	
	@Column(name = "balance")
	private Long balance;
	
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "email", column = @Column(name = "email", unique = true)),
        @AttributeOverride(name = "password", column = @Column(name = "password"))
    })
	private Credential credentials;
	
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "route", column = @Column(name = "user_address_route")),
        @AttributeOverride(name = "number", column = @Column(name = "user_address_number")),
        @AttributeOverride(name = "city", column = @Column(name = "user_address_city"))
    })
    private Address address;

    @JsonManagedReference
    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> publishedPosts;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> publishedReviews;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "target", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> receivedReviews;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> publishedOffers;
}














