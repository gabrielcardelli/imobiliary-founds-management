package project.mundofii.dtos;

import lombok.Getter;
import lombok.Setter;

public class CheckAuthenticationDTO {

	@Getter @Setter
	private String ip;

	@Getter @Setter
	private String token;

	@Getter @Setter
	private String personId;

}
