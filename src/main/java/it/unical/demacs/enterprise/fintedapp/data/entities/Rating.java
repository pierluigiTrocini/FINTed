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
public class Rating {

	@Column
	private Long avgRating;
	
	@Column
	private Integer rates;
	
}
