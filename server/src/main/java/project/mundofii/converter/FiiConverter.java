package project.mundofii.converter;

import java.text.NumberFormat;

import org.springframework.stereotype.Component;

import project.mundofii.domain.Fii;
import project.mundofii.dtos.FiiDTO;

@Component
public class FiiConverter implements Converter<Fii,FiiDTO>{

	@Override
	public FiiDTO convertToDTO(Fii t1) {
		
		FiiDTO dto = new FiiDTO();
		dto.setId(t1.getId());
		dto.setName(t1.getName());
		dto.setLastPrice(t1.getLastPriceFormatted());
		
		return dto;
	}

	@Override
	public Fii convertToReal(FiiDTO t2) {
		// TODO Auto-generated method stub
		return null;
	}

}
