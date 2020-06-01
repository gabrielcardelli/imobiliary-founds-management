package project.mundofii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.mundofii.domain.Authentication;
import project.mundofii.dtos.CheckAuthenticationDTO;
import project.mundofii.repository.AuthenticationRepository;

@Service
public class CheckPersonAuthenticationService implements AbstractService<CheckAuthenticationDTO,  Boolean>{

	private @Autowired AuthenticationRepository authenticationRepository;
	
	@Override
	public Boolean businessRule(CheckAuthenticationDTO t) {

		Authentication auth = authenticationRepository.findByTokenAndIpAndPersonId(t.getToken(),t.getIp(),t.getPersonId());
		
		if(auth != null && auth.isValid()) {
			
			auth.logRequest();
			
			authenticationRepository.save(auth);
			
			return true;
			
		}else {
			
			return false;
			
		}
		
	}

	@Override
	public ServiceValidationResult validation(CheckAuthenticationDTO t) {
		return new ServiceValidationResult();
	}

}
