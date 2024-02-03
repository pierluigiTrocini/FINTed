package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class OfferDto {
	
    private Long id;


    private PostDto post;


    private UserDto user;


    private Long offer;


    private Date publishDate;
}
