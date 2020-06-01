package project.mundofii.services;

import org.springframework.beans.factory.annotation.Autowired;

import project.mundofii.domain.Fii;
import project.mundofii.dtos.FiiDTO;
import project.mundofii.repository.FiiRepository;
import org.springframework.stereotype.Service;


@Service
public class AddFiiService  implements AbstractService<FiiDTO,Void>{

	private @Autowired FiiRepository fiiRepository;
	
	@Override
	public Void businessRule(FiiDTO t) {
		
		Fii fii = new Fii();
		fii.setName(t.getName());
		
		fiiRepository.save(fii);
		
		return null;
	}

	@Override
	public ServiceValidationResult validation(FiiDTO t) {
		// TODO Auto-generated method stub
		return new ServiceValidationResult();
	}

}
