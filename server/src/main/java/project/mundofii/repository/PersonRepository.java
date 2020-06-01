package project.mundofii.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.mundofii.domain.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, String>{
	
	public Person findByEmail(String email);
	
}
