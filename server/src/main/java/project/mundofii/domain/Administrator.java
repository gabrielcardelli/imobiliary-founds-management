package project.mundofii.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class Administrator {
	
	@Id
	@Getter 
	private Long id;
	
	@Getter @Setter
	private String name;

}
