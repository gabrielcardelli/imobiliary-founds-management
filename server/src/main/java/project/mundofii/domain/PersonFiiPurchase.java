package project.mundofii.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class PersonFiiPurchase {

	@Id
	@Getter
	private String id;
	
	@DBRef
	@Getter @Setter
	private PersonFii personFii;
	
	@Getter @Setter
	private int numberOfQuotas;
	
	@Getter @Setter
	private BigDecimal amountPerQuota;
	
	@Getter @Setter
	private Calendar date;

	@Transient
	public String getAmountPerQuotaFormatted() {
		return NumberFormat.getCurrencyInstance().format(amountPerQuota);
	}

	@Transient
	public String getDateFormatted() {
		return new SimpleDateFormat("dd/MM/yyyy").format(date.getTime());
	}	
	
}
