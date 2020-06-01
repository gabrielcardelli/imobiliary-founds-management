package project.mundofii.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import project.mundofii.domain.PersonFii;
import project.mundofii.domain.PersonFiiPurchase;

public interface PersonFiiPurchaseRepository  extends CrudRepository<PersonFiiPurchase, Long>{

	List<PersonFiiPurchase> findByPersonFii(PersonFii personFii);
	
}
