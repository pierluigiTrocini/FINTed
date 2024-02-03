package it.unical.demacs.enterprise.fintedapp.dto;


import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class UserDto {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private Date registrationDate;
	
}
