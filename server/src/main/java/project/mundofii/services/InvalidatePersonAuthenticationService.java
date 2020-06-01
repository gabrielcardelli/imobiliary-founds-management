package project.mundofii.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.mundofii.domain.Authentication;
import project.mundofii.dtos.CheckAuthenticationDTO;
import project.mundofii.repository.AuthenticationRepository;
import project.mundofii.services.excetion.AuthenticationAlreadyInvalidatedException;

@Service
public class InvalidatePersonAuthenticationService implements AbstractService<String,  Boolean>{

	private @Autowired AuthenticationRepository authenticationRepository;
	
	@Override
	public Boolean businessRule(String t) {

		Authentication auth = authenticationRepository.findByToken(t);
		
		if(auth != null) {
			
			try {
				
				auth.invalidate();
				authenticationRepository.save(auth);
				
			}catch(AuthenticationAlreadyInvalidatedException e) {
				// ok :) it's fine.
			}
			
			return true;
			
		}else {
			
			return true;
			
		}
		
	}

	@Override
	public ServiceValidationResult validation(String t) {
		return new ServiceValidationResult();
	}

}
