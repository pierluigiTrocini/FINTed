package it.unical.demacs.enterprise.fintedapp.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class TokenResponse {
	private String access_token;
	private int expires_in;
	private int refresh_expires_in;
	private String refresh_token;
	private String token_type;
	private int not_before_policy;
	private String session_state;
	private String scope;
}
