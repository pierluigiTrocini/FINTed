package it.unical.demacs.enterprise.fintedapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthConverter jwtAuthConverter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.cors(cors -> cors.disable());
		http.authorizeHttpRequests(req -> {
			req.requestMatchers(
					"/v3/api-docs/**", 
					"/swagger-ui/**",
					"/users/all/{page}",
					"/posts/user/{username}",
					"/posts/all/{page}",
					"/reviews/target/{username}").permitAll();
			req.requestMatchers(HttpMethod.POST, "/users/").permitAll();
			req.requestMatchers(HttpMethod.GET, "/posts/").permitAll();
			req.requestMatchers(HttpMethod.POST, "/auth/login").permitAll();
			req.requestMatchers(HttpMethod.POST, "/auth/logout").permitAll();

			
			req.anyRequest().authenticated();
		});
		http.oauth2ResourceServer(oauth2 -> {
			oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter));
		});
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

}
