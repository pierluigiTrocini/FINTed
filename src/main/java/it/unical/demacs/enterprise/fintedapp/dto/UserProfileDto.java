package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@SuppressWarnings(value = { "unused" })
@ToString
public class UserProfileDto {
	
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private Date registrationDate;
	
	@Email
	private String credentialsEmail;
	
	private String addressRoute;
	
	private String addressNumber;
	
	private String addressCity;
	
    private List<PostDto> publishedPosts;

    private List<ReviewDto> receivedReviews;
}
