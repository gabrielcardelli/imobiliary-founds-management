package project.mundofii.dtos;

import lombok.Getter;
import lombok.Setter;

public class AuthenticationDTO {
	
	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String password;

}
