package project.mundofii.converter;

import org.springframework.stereotype.Component;

import project.mundofii.domain.Comment;
import project.mundofii.dtos.CommentDTO;

@Component
public class CommentConverter implements Converter<Comment,CommentDTO>{

	@Override
	public CommentDTO convertToDTO(Comment t1) {
		
		CommentDTO dto = new CommentDTO();
		dto.setComment(t1.getComment());
		
		return dto;
	}

	@Override
	public Comment convertToReal(CommentDTO t2) {
		return null;
	}

}
