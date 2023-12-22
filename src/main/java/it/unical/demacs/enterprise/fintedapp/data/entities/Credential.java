package it.unical.demacs.enterprise.fintedapp.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credential {

	@Column
	private String email;
	
	@Column
	private String password;
	
}
