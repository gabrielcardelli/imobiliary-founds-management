package project.mundofii.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class Person {
	
	@Id
	@Getter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String password;

}
