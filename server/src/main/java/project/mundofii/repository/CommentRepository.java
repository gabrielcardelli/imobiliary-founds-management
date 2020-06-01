package project.mundofii.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import project.mundofii.domain.Comment;
import project.mundofii.domain.Fii;

public interface CommentRepository extends CrudRepository<Comment,Long>{

	public List<Comment> findByFiiOrderByIdDesc(Fii fii, Pageable pageable);
	
}
