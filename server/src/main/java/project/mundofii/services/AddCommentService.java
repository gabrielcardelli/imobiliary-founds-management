package project.mundofii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.mundofii.domain.Comment;
import project.mundofii.dtos.CommentDTO;
import project.mundofii.repository.CommentRepository;
import project.mundofii.repository.FiiRepository;

@Service
public class AddCommentService implements AbstractService<CommentDTO,Void>{

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private FiiRepository fiiRepository;
	
	@Transactional
	@Override
	public Void businessRule(CommentDTO t) {
	
		Comment comment = new Comment();
		comment.setFii(fiiRepository.findById(t.getFiiId()).get());
		comment.setComment(t.getComment());
		
		commentRepository.save(comment);
		
		return null;
	}

	@Override
	public ServiceValidationResult validation(CommentDTO t) {
		// TODO Auto-generated method stub
		return new ServiceValidationResult();
	}

}
