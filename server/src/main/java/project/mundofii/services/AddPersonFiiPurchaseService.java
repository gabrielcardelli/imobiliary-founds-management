package project.mundofii.services;

import java.text.ParseException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.mundofii.domain.PersonFiiPurchase;
import project.mundofii.dtos.PurchaseDTO;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PersonFiiRepository;
import project.mundofii.repository.PersonRepository;
import project.mundofii.utils.DateUtils;
import project.mundofii.repository.PersonFiiPurchaseRepository;

@Service
public class AddPersonFiiPurchaseService  implements AbstractService<PurchaseDTO,Void>{

	private @Autowired PersonFiiRepository personFiiRepository;
	
	private @Autowired PersonFiiPurchaseRepository purchaseRepository;
	
	private @Autowired PersonRepository personRepository;
	
	private @Autowired FiiRepository fiiRepository;
	
	@Override
	public Void businessRule(PurchaseDTO t) {
		
		PersonFiiPurchase purchase = new PersonFiiPurchase();
		
		try {
			purchase.setDate(DateUtils.convertStringToCalendar(t.getDate(), "yyyy-MM-dd"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		purchase.setPersonFii(personFiiRepository.findByPersonAndFii(personRepository.findById(t.getPersonId()).get(), fiiRepository.findById(t.getFiiId()).get()));
		purchase.setNumberOfQuotas(t.getNumberOfQuotas());
		purchase.setAmountPerQuota(t.getAmountPerQuota());

		purchaseRepository.save(purchase);
		
		return null;
		
	}

	@Override
	public ServiceValidationResult validation(PurchaseDTO t) {
		return new ServiceValidationResult();
	}

}
