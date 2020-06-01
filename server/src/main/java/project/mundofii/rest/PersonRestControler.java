package project.mundofii.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.mundofii.dtos.PersonDTO;
import project.mundofii.services.AddPersonService;


@RestController
@CrossOrigin
public class PersonRestControler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private @Autowired AddPersonService addPersonService;
	
	@RequestMapping(path="/people",method=RequestMethod.POST)
	public ResponseEntity<Void> register(PersonDTO person){
		
		log.info("Registering person : " + person.getName());
		
		addPersonService.execute(person);
	
		return ResponseEntity.ok().build();
	
	}
	
}
