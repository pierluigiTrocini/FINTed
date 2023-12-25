package it.unical.demacs.enterprise.fintedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@SpringBootApplication
public class FintedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintedAppApplication.class, args);
	}

}
