package project.mundofii.services;

import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import project.mundofii.domain.Authentication;
import project.mundofii.domain.Person;
import project.mundofii.dtos.AuthenticationDTO;
import project.mundofii.repository.AuthenticationRepository;
import project.mundofii.repository.PersonRepository;
import project.mundofii.services.excetion.WrongCredentialsException;

@Service
public class AuthenticatePersonService implements AbstractService<AuthenticationDTO,  Map<String,Object>>{

	private @Autowired PersonRepository personRepository;
	
	private @Autowired AuthenticationRepository authenticationRepository;
	
	@Override
	public Map<String,Object> businessRule(AuthenticationDTO t) {

		Person person = personRepository.findByEmail(t.getEmail());

		System.out.println("Person: " + person);
		
		System.out.println(new BCryptPasswordEncoder(11).matches(t.getPassword(), person.getPassword()));
		
		if(person != null) {
			
			if(new BCryptPasswordEncoder(11).matches(t.getPassword(), person.getPassword())) {
			
				Authentication auth = new Authentication();
				
				auth.setPerson(person);
				
				Calendar actualDate = Calendar.getInstance();
				
				auth.setCreateDate(actualDate);

				String plainClientCredentials= person.getEmail() + ":" + auth.getDateForPasswordFormatted();
				String base64ClientCredentials = new String(Base64.getEncoder().encodeToString((plainClientCredentials.getBytes())));

				auth.setToken(base64ClientCredentials);
				
				authenticationRepository.save(auth);
				
				Map<String,Object> returnMap = new HashMap<>();
				returnMap.put("authKey", base64ClientCredentials);
				returnMap.put("personId", person.getId());
				returnMap.put("personName", person.getName());
				
				return returnMap;
			}
			
		}
	
		throw new WrongCredentialsException();
		
	}

	@Override
	public ServiceValidationResult validation(AuthenticationDTO t) {
		return new ServiceValidationResult();
	}

}
