package project.mundofii.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class Comment {

	@Id
	@Getter
	private String id;
	
	@DBRef
	@Getter @Setter
	private Fii fii;
	
	@Getter @Setter 
	private String comment;
	
}
