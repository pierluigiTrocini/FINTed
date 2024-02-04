package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@SuppressWarnings(value = { "unused" })
public class ReviewDto {
    private Long id;
    
    @NotNull
    private Long authorId;

    @NotNull
    private Long targetId;

    @NotEmpty
    private Date publishDate;

    @NotEmpty
    private String content;
}
