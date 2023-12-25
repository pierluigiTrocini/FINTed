package it.unical.demacs.enterprise.fintedapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.unical.demacs.enterprise.fintedapp.handler.DateManager;

@SpringBootApplication
public class FintedAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintedAppApplication.class, args);
	}

}
