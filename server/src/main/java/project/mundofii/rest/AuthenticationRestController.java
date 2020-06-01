package project.mundofii.rest;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.mundofii.dtos.AuthenticationDTO;
import project.mundofii.dtos.CheckAuthenticationDTO;
import project.mundofii.services.AuthenticatePersonService;
import project.mundofii.services.CheckPersonAuthenticationService;
import project.mundofii.services.InvalidatePersonAuthenticationService;
import project.mundofii.services.excetion.WrongCredentialsException;

@RestController
@CrossOrigin
public class AuthenticationRestController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private @Autowired AuthenticatePersonService authenticatePersonService;
	
	private @Autowired CheckPersonAuthenticationService checkPersonAuthenticationService;
	
	private @Autowired InvalidatePersonAuthenticationService invalidatePersonAuthenticationService;
	
	@RequestMapping(path="/authentication",method=RequestMethod.POST)
	public ResponseEntity<Map<String,Object>> all(AuthenticationDTO dto){
		
		log.info("Authenticating person " + dto.getEmail() + " with password " + dto.getPassword());
		
		try {
			return ResponseEntity.ok(authenticatePersonService.execute(dto).getObject());
		}catch(WrongCredentialsException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@RequestMapping(path="/authentication/check",method=RequestMethod.POST)
	public ResponseEntity<Boolean> check(CheckAuthenticationDTO dto, HttpServletRequest request){
		
		dto.setIp(request.getRemoteAddr());
		
		log.info("Checking Authentication of person " + dto.getPersonId() + " with token " + dto.getToken() + " and ip " + dto.getIp());
		
		try {
			return ResponseEntity.ok(checkPersonAuthenticationService.execute(dto).getObject());
		}catch(WrongCredentialsException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}
	
	@RequestMapping(path="/authentication/invalidate",method=RequestMethod.POST)
	public ResponseEntity<Boolean> invalidate(@RequestHeader(value="Authorization") String authorization){

		log.info("Invalidating authentication token " +  authorization);
		
		try {
			return ResponseEntity.ok(invalidatePersonAuthenticationService.execute(authorization).getObject());
		}catch(WrongCredentialsException exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}

}
