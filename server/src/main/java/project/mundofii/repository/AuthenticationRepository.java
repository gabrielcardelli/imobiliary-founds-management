package project.mundofii.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.mundofii.domain.Authentication;
import project.mundofii.domain.Person;

@Repository
public interface AuthenticationRepository extends CrudRepository<Authentication, String>{

	public List<Authentication> findByPerson(Person person);

	public Authentication findByTokenAndIpAndPersonId(String token, String ip, String personId);
	
	public Authentication findByToken(String token);
	
}
