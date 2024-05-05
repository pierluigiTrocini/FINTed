package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import it.unical.demacs.enterprise.fintedapp.data.entities.PostStatus;
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
public class PostBasicInfoDto {
	private Long id;

	@NotEmpty
	private String title;
	
	@NotNull
	private Long sellerId;
	
	@NotNull
	private String sellerUsername;
	
	private Date publishedDate;
	
	@PositiveOrZero
	private Long startingPrice;
	
	private PostStatus status;
}