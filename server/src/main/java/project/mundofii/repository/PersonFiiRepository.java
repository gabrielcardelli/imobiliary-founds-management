package project.mundofii.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.mundofii.domain.Fii;
import project.mundofii.domain.PersonFii;
import project.mundofii.domain.Person;

@Repository
public interface PersonFiiRepository extends CrudRepository<PersonFii, Long>{

	public PersonFii findByPersonAndFii(Person person,Fii fii);
	
	public List<PersonFii> findByPerson(Person person);
	
}
