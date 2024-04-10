package it.unical.demacs.enterprise.fintedapp.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import it.unical.demacs.enterprise.fintedapp.data.entities.OfferStatus;
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
    private Long postId;
    
    private String postTitle;

    @NotNull
    private Long userId;
    
    private String userUsername;
    
    private String userFirstName;
    
    private String userLastName;
    
    private Long postSellerId;
    
    @NotNull
    private String postSellerUsername;

    private Long offer;

    private Date publishDate;
    
    private OfferStatus offerStatus;
    
    private String speditionSpeditionCode;
    
}
