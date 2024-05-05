package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value =  { "unused" })
public class SpeditionDto {
	
	private Long id;
	
	private String sellerUsername;
	
	private String purchaserFirstName;
	
	private String purchaserLastName;
	
	private String purchaserUsername;
	
	private String purchaserAddressRoute;
	
	private String purchaserAddressNumber;
	
	private String purchaserAddressCity;
	
	private Date date;
	
	private String speditionCode;

}
