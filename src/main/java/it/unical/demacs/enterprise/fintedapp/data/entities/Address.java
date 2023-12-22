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
public class Address {

	@Column
	private String route;
	
	@Column
	private String number;
	
	@Column
	private String city;
	
}
