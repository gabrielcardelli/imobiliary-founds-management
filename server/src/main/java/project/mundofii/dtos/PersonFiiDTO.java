package project.mundofii.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

public class PersonFiiDTO {
	
	@Getter @Setter
	private String id;
	
	@Getter @Setter
	private String personId;
	
	@Getter @Setter
	private String fiiId;
	
	@Getter @Setter
	private String fiiType;
	
	@Getter @Setter
	private String fiiName;

	@Getter @Setter
	private String fiiLastPriceFormatted;
	
	@Getter @Setter
	private BigDecimal fiiLastPrice;
	
	@Getter @Setter
	private int amountOfQuotas;
	
	@Getter @Setter
	private String receivedDebts;
	
	@Getter @Setter
	private BigDecimal investedAmount;
	
	@Getter @Setter
	private String investedAmountFormatted;
	
	@Getter @Setter
	private BigDecimal currentAmount;
	
	@Getter @Setter
	private String currentAmountFormatted;

	@Getter @Setter
	private BigDecimal purchaseAveragePrice;
	
	@Getter @Setter
	private String purchaseAveragePriceFormatted;
}
