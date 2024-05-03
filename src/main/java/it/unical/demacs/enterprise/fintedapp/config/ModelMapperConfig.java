package it.unical.demacs.enterprise.fintedapp.config;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import it.unical.demacs.enterprise.fintedapp.data.entities.User;
import it.unical.demacs.enterprise.fintedapp.dto.UserPersonalProfileDto;


@Configuration
public class ModelMapperConfig {
	  @Bean
	  public ModelMapper getModelMapper() {
	    ModelMapper modelMapper = new ModelMapper();
	    modelMapper.getConfiguration().setFieldMatchingEnabled(true).setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);	    
	    return modelMapper;
	  }
}
