package project.mundofii.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class PurchaseDTO {
	
	@Getter @Setter
	private String date;
	
	@Getter @Setter
	private int numberOfQuotas;
	
	@Getter @Setter
	private BigDecimal amountPerQuota;
	
	@Getter @Setter
	private String amountPerQuotaFormatted;
	
	@Getter @Setter
	private String personId;
	
	@Getter @Setter
	private String fiiId;

}
