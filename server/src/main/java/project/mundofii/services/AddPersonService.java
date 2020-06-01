package project.mundofii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.mundofii.converter.PersonConverter;
import project.mundofii.domain.Person;
import project.mundofii.dtos.PersonDTO;
import project.mundofii.repository.PersonRepository;

@Service
public class AddPersonService implements AbstractService<PersonDTO, Void>{

	private @Autowired PersonConverter personConverter;
	
	private @Autowired PersonRepository personRepository;
	
	@Transactional
	@Override
	public Void businessRule(PersonDTO personDTO) {

		Person person = personConverter.convertToReal(personDTO);
		
		System.out.println("Criptografando " + personDTO.getPassword());
		System.out.println("Resultado " + new BCryptPasswordEncoder(11).encode(person.getPassword()));
		System.out.println("Resultado " + new BCryptPasswordEncoder(11).encode(person.getPassword()));
		System.out.println("Resultado " + new BCryptPasswordEncoder(11).encode(person.getPassword()));
		
		person.setPassword(new BCryptPasswordEncoder(11).encode(person.getPassword()));
		
		personRepository.save(person);
		
		return null;
	}

	@Override
	public ServiceValidationResult validation(PersonDTO t) {
		return new ServiceValidationResult();
	}

}
