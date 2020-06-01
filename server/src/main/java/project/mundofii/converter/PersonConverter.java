package project.mundofii.converter;

import org.springframework.stereotype.Component;

import project.mundofii.domain.Person;
import project.mundofii.dtos.PersonDTO;


@Component
public class PersonConverter implements Converter<Person,PersonDTO>{

	@Override
	public PersonDTO convertToDTO(Person t1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person convertToReal(PersonDTO t2) {
		
		Person person = new Person();
		person.setName(t2.getName());
		person.setEmail(t2.getEmail());
		person.setPassword(t2.getPassword());
		
		return person;
	}
	
}
