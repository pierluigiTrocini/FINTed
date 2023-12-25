package it.unical.demacs.enterprise.fintedapp.dto;

import java.sql.Date;

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

    @NotNull
    private PostDto post;

    @NotNull
    private UserDto user;

    @NotEmpty
    private Long offer;

    @NotEmpty
    private Date publishDate;
}
