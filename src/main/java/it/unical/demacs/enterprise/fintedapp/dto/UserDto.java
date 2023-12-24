package it.unical.demacs.enterprise.fintedapp.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class UserDto {

	@NotEmpty
	private Long id;
	
	@NotEmpty
	private String firstName;
	
	@NotEmpty
	private String lastName;
	
	@NotEmpty
	private String username;
	
	private Date registrationDate;
	
}
