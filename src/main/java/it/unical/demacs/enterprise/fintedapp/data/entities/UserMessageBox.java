package it.unical.demacs.enterprise.fintedapp.data.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "userMessageBox")
public class UserMessageBox {
	
	@Data
	@AllArgsConstructor
	public class Message{
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@Field("timestamp")
		private Date timestamp;
		
		@Field("sender")
		private Long senderId;
		
		@Field("receiver")
		private Long receiverId;
		
		@Field("content")
		private String content;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Field("userId")
	private Long userId;
	
	@DBRef
	private List<Message> messages;
	
}
