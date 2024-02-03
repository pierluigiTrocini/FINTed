package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UserRegistrationDto {
	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private String username;
	
	private Date registrationDate;
	
	
	@NotEmpty
	private String credentialsEmail;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*\\\\d).{8,}$\r\n")
	private String credentialsPassword;
	
    @NotEmpty
    private String addressRoute;
    
    @NotEmpty
    private String addressNumber;
    
    @NotEmpty
    private String addressCity;

}
