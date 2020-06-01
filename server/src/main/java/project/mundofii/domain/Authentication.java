package project.mundofii.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import project.mundofii.services.excetion.AuthenticationAlreadyInvalidatedException;

@Document
public class Authentication {
	
	@Id
	@Getter 
	private String id;
	
	@Getter @Setter
	private String token;

	@Getter @Setter
	private Calendar createDate;
	
	@Getter @Setter
	private Calendar lastRequestDate;
	
	@Getter @Setter
	private boolean invalidated;
	
	@Getter @Setter
	private String ip;
	
	@DBRef
	@Getter @Setter
	private Person person;
	
	@Transient
	public String getDateForPasswordFormatted() {
		return new SimpleDateFormat("ddMMyyyyhhmm").format(createDate.getTime());
	}

	@Transient
	public boolean isValid() {
		
		Calendar lastRequest = lastRequestDate != null ? (Calendar) lastRequestDate.clone() : (Calendar) createDate.clone();
		
		lastRequest.add(Calendar.MINUTE, 15);
		
		return lastRequest.after(Calendar.getInstance()) && !invalidated;
	
	}

	@Transient
	public void logRequest() {
		lastRequestDate = Calendar.getInstance();
	}

	@Transient
	public void invalidate() {
		
		if(invalidated) {
			throw new AuthenticationAlreadyInvalidatedException();
		}
		
		invalidated = true;
		
	}
	
}
