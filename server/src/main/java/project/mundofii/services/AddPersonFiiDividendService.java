package project.mundofii.services;

import java.text.ParseException;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.mundofii.domain.PersonFiiDividend;
import project.mundofii.dtos.PersonFiiDTO;
import project.mundofii.dtos.ReceivedDividendDTO;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PersonFiiRepository;
import project.mundofii.repository.PersonRepository;
import project.mundofii.utils.DateUtils;
import project.mundofii.repository.PersonFiiDividendRepository;

@Service
public class AddPersonFiiDividendService  implements AbstractService<ReceivedDividendDTO,Void>{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Repositories
	
	private @Autowired PersonFiiRepository personFiiRepository;
	
	private @Autowired PersonFiiDividendRepository personFiiDividendRepository;
	
	private @Autowired PersonRepository personRepository;
	
	private @Autowired FiiRepository fiiRepository;
	
	
	// Methods
	
	@Override
	public Void businessRule(ReceivedDividendDTO t) {
		
		PersonFiiDividend r = new PersonFiiDividend();
	
		try {
			r.setPaymentDate(DateUtils.convertStringToCalendar(t.getDate(),"yyyy-MM-dd"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		r.setPersonFii(personFiiRepository.findByPersonAndFii(personRepository.findById(t.getPersonId()).get(), fiiRepository.findById(t.getFiiId()).get()));
		r.setNumberOfQuotas(t.getNumberOfQuotas());
		r.setAmountPerQuota(t.getAmountPerQuota());

		personFiiDividendRepository.save(r);
		
		return null;

	}

	@Override
	public ServiceValidationResult validation(ReceivedDividendDTO t) {
		// TODO Auto-generated method stub
		return new ServiceValidationResult();
	}

}
