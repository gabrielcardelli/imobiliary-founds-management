package project.mundofii.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.mundofii.domain.Fii;
import project.mundofii.domain.Person;
import project.mundofii.domain.PersonFii;
import project.mundofii.domain.PersonFiiDividend;
import project.mundofii.domain.PersonFiiPurchase;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PersonFiiDividendRepository;
import project.mundofii.repository.PersonFiiPurchaseRepository;
import project.mundofii.repository.PersonFiiRepository;
import project.mundofii.repository.PersonRepository;

@Service
public class DeleteFiiOfPersonService  implements AbstractService<Map<String,Object>,Void>{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Repositories
	
	private @Autowired PersonFiiRepository personFiiRepository;

	private @Autowired PersonFiiDividendRepository personFiiDividendRepository;
	
	private @Autowired PersonFiiPurchaseRepository personFiiPurchaseRepository;
	
	private @Autowired PersonRepository personRepository;
	
	private @Autowired FiiRepository fiiRepository;
	
	
	// Methods
	
	@Transactional
	@Override
	public Void businessRule(Map<String,Object> params) {
		
		Person person = personRepository.findById(params.get("personId").toString()).get();
		
		Fii fii = fiiRepository.findById(params.get("fiiId").toString()).get();
		
		PersonFii personFii = personFiiRepository.findByPersonAndFii(person, fii);
		
		List<PersonFiiPurchase> purchases = personFiiPurchaseRepository.findByPersonFii(personFii);
		
		List<PersonFiiDividend> dividends = personFiiDividendRepository.findByPersonFii(personFii);
		
		if(purchases != null) {
			purchases.forEach(purchase -> {
				personFiiPurchaseRepository.delete(purchase);
			});
		}
		
		if(dividends != null) {
			dividends.forEach(dividend -> {
				personFiiDividendRepository.delete(dividend);
			});
		}
		
		personFiiRepository.delete(personFii);
		
		return null;

	}

	@Override
	public ServiceValidationResult validation(Map<String,Object> params) {
		// TODO Auto-generated method stub
		return new ServiceValidationResult();
	}

}
