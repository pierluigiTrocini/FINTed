package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.List;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuppressWarnings(value = { "unused" })
public class UserPersonalProfileDto extends UserProfileDto {
	
	@PositiveOrZero
	private Long balance;
	
	private List<OfferDto> offersPublished;

}
