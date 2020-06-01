package project.mundofii.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import project.mundofii.domain.PersonFii;
import project.mundofii.dtos.PersonFiiDTO;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PersonFiiRepository;
import project.mundofii.repository.PersonRepository;
import project.mundofii.services.excetion.FiiAlreadyInPersonWalletException;

@Repository
public class AddFiiToPersonService implements AbstractService<PersonFiiDTO,Void>{

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	private @Autowired PersonRepository personRepository;
	
	private @Autowired FiiRepository fiiRepository;
	
	private @Autowired PersonFiiRepository personFiiRepository;
	
	@Transactional
	@Override
	public Void businessRule(PersonFiiDTO t) {
		
		log.debug("Saving fii " + t.getFiiId() + " to person " + t.getPersonId() + " .");
		
		PersonFii personFii = new PersonFii();
		personFii.setPerson(personRepository.findById(t.getPersonId()).get());
		personFii.setFii(fiiRepository.findById(t.getFiiId()).get());
		
		personFiiRepository.save(personFii);
		
		return null;
	}

	@Override
	public ServiceValidationResult validation(PersonFiiDTO t) {
		
		PersonFii fiiWallet = 
				personFiiRepository.findByPersonAndFii(personRepository.findById(t.getPersonId()).get(), fiiRepository.findById(t.getFiiId()).get());
		
		if(fiiWallet != null) {
			throw new FiiAlreadyInPersonWalletException();
		}
		
		return new ServiceValidationResult();
		
	}

}
