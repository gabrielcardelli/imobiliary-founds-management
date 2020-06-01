package project.mundofii.dtos;

import lombok.Getter;
import lombok.Setter;

public class CommentDTO {
	
	@Getter @Setter
	private String fiiId;
	
	@Getter @Setter
	private Long parentId;
	
	@Getter @Setter
	private String comment;
	
	@Override
	public String toString() {
		return "Fii ID: " + fiiId + " - Parent ID: " + parentId + " - Comment: " + comment;
	}

}
