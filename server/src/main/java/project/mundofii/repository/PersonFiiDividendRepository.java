package project.mundofii.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.mundofii.domain.PersonFii;
import project.mundofii.domain.PersonFiiDividend;

@Repository
public interface PersonFiiDividendRepository extends CrudRepository<PersonFiiDividend, Long>{

	List<PersonFiiDividend> findByPersonFii(PersonFii personFii);

}
