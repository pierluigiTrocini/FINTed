package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "spedition")
@NoArgsConstructor
public class Spedition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Offer offer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User seller;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User purchaser;
	
	private String speditionCode;
	
	private Date date;
	
	@PrePersist
	private void generateSpeditionCode() {
		byte bytes[] = new byte[20];
		(new SecureRandom()).nextBytes(bytes);
		speditionCode = Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
	}
}









