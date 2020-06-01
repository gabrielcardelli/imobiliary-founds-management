package project.mundofii.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project.mundofii.domain.PersonFii;
import project.mundofii.dtos.PersonFiiDTO;
import project.mundofii.repository.PersonFiiDividendRepository;
import project.mundofii.repository.PersonFiiPurchaseRepository;

@Component
public class PersonFiiConverter implements Converter<PersonFii,PersonFiiDTO>{

	@Autowired
	private PersonFiiPurchaseRepository personFiiPurchaseRepository;
	
	@Autowired
	private PersonFiiDividendRepository personFiiDividendRepository;
	
	@Override
	public PersonFiiDTO convertToDTO(PersonFii real) {
		
		PersonFiiDTO dto = new PersonFiiDTO();
		
		dto.setId(real.getId());
		dto.setFiiType(real.getFii().getType().toString());
		dto.setFiiId(real.getFii().getId());
		dto.setFiiName(real.getFii().getName());
		dto.setAmountOfQuotas(real.getNumberOfQuotas(personFiiPurchaseRepository));
		dto.setCurrentAmountFormatted(real.getCurrentAmountFormatted(personFiiPurchaseRepository));
		dto.setInvestedAmountFormatted(real.getInvestedAmountFormatted(personFiiPurchaseRepository));
		dto.setReceivedDebts(real.getReceivedDebtsFormatted(personFiiDividendRepository));
		dto.setFiiLastPriceFormatted(real.getFii().getLastPriceFormatted());
		dto.setFiiLastPrice(real.getFii().getLastPrice());
		dto.setPurchaseAveragePriceFormatted(real.getAveragePriceFormatted(personFiiPurchaseRepository));
		
		
		return dto;
	}

	@Override
	public PersonFii convertToReal(PersonFiiDTO t2) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
