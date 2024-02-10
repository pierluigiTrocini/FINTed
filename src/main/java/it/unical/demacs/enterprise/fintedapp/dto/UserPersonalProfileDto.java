package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class UserPersonalProfileDto {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private Date registrationDate;
	
	private Long balance;
	
	@Email
	private String credentialsEmail;
	
	private String addressRoute;
	
	private String addressNumber;
	
	private String addressCity;
	
    private List<PostDto> publishedPosts;

    private List<ReviewDto> receivedReviews;
    
    private List<ReviewDto> publishedReviews;
    
    private List<OfferDto> publishedOffers;

}
