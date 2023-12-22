package it.unical.demacs.enterprise.fintedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class FintedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintedAppApplication.class, args);
		
	}

}
