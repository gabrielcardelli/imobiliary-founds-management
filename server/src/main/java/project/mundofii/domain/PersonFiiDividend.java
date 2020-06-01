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
public class PersonFiiDividend {

	@Id
	@Getter @Setter
	private String id;
	
	@DBRef
	@Getter @Setter
	private PersonFii personFii;
	
	@Getter @Setter
	private Calendar paymentDate;
	
	@Getter @Setter
	private int numberOfQuotas;
	
	@Getter @Setter
	private BigDecimal amountPerQuota;

	@Transient
	public String getPaymentDateFormatted() {
		return new SimpleDateFormat("dd/MM/yyyy").format(paymentDate.getTime());
	}

	@Transient
	public String getAmountReceivedFormatted() {
		return NumberFormat.getCurrencyInstance().format(amountPerQuota.multiply(new BigDecimal(numberOfQuotas)));
	}
	
	@Transient
	public String getAmountPerQuotaFormatted() {
		return NumberFormat.getCurrencyInstance().format(amountPerQuota);
	}
	
}
