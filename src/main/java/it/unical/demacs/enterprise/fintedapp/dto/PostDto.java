package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = {"unused"})
public class PostDto {
	
	private Long id;

	private String title;
	
	@NotNull
	private Long sellerId;
	
	@NotNull
	private String sellerUsername;
	
	private Date publishedDate;
	
	@PositiveOrZero
	private Long startingPrice;
	
//	private String postImage;
	
//	private List<OfferDto> offers;

}
