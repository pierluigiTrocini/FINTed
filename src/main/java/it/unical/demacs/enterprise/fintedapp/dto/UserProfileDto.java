package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings(value = { "unused" })
public class UserProfileDto extends UserDto{
	
	@Email
	private String credentialsEmail;
	
	private String addressRoute;
	
	private String addressNumber;
	
	private String addressCity;
	
    private List<PostDto> publishedPosts;

    private List<ReviewDto> receivedReviews;

}
