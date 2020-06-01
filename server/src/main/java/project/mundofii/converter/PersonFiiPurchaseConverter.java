package project.mundofii.converter;

import org.springframework.stereotype.Component;

import project.mundofii.domain.PersonFiiPurchase;
import project.mundofii.dtos.PurchaseDTO;

@Component
public class PersonFiiPurchaseConverter implements Converter<PersonFiiPurchase,PurchaseDTO> {

	@Override
	public PurchaseDTO convertToDTO(PersonFiiPurchase t1) {
		PurchaseDTO dto = new PurchaseDTO();
		dto.setDate(t1.getDateFormatted());
		dto.setNumberOfQuotas(t1.getNumberOfQuotas());
		dto.setAmountPerQuotaFormatted(t1.getAmountPerQuotaFormatted());
		return dto;
	}

	@Override
	public PersonFiiPurchase convertToReal(PurchaseDTO t2) {
		// TODO Auto-generated method stub
		return null;
	}

}
