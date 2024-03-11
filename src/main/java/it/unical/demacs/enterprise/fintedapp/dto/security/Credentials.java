package it.unical.demacs.enterprise.fintedapp.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class Credentials {
	private String username;
	private String password;
}
