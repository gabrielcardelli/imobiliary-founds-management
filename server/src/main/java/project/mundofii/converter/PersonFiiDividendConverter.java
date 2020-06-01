package project.mundofii.converter;

import org.springframework.stereotype.Component;

import project.mundofii.domain.PersonFiiDividend;
import project.mundofii.dtos.ReceivedDividendDTO;

@Component
public class PersonFiiDividendConverter implements Converter<PersonFiiDividend,ReceivedDividendDTO> {

	@Override
	public ReceivedDividendDTO convertToDTO(PersonFiiDividend t1) {
		ReceivedDividendDTO dto = new ReceivedDividendDTO();
		dto.setDate(t1.getPaymentDateFormatted());
		dto.setNumberOfQuotas(t1.getNumberOfQuotas());
		dto.setAmountPerQuotaFormatted(t1.getAmountPerQuotaFormatted());
		dto.setAmountReceivedFormatted(t1.getAmountReceivedFormatted());
		return dto;
	}

	@Override
	public PersonFiiDividend convertToReal(ReceivedDividendDTO t2) {
		return null;
	}

}
