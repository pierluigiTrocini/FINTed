package it.unical.demacs.enterprise.fintedapp.dto.security;

import java.util.Date;

import it.unical.demacs.enterprise.fintedapp.data.entities.OfferStatus;
import it.unical.demacs.enterprise.fintedapp.dto.OfferDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class RefreshTokenRequest {
	private String grant_type;
	private String client_id;
	private String refresh_token;
}
