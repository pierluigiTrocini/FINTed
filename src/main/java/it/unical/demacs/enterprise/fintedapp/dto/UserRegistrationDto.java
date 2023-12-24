package it.unical.demacs.enterprise.fintedapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString
@NoArgsConstructor
public class UserRegistrationDto extends UserDto{
	
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
