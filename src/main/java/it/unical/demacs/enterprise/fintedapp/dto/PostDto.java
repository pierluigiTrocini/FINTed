package it.unical.demacs.enterprise.fintedapp.dto;

import java.sql.Date;

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
	
	@NotNull
	private UserDto seller;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	@PositiveOrZero
	private Long startingPrice;
	
	@NotEmpty
	private String postImage;
	
	@NotEmpty
	private Date publishDate;
	
	@NotEmpty
	private UserDto buyer;

}
