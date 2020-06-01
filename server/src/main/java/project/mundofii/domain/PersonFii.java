package project.mundofii.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.Getter;
import lombok.Setter;
import project.mundofii.repository.PersonFiiDividendRepository;
import project.mundofii.repository.PersonFiiPurchaseRepository;

@Document
public class PersonFii {

	@Id
	@Getter
	private String id;
	
	@DBRef
	@Getter @Setter
	private Person person;
	
	@DBRef
	@Getter @Setter
	private Fii fii;

	public String getAveragePriceFormatted(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
		
		int numberOfQuotas = getNumberOfQuotas(personFiiPurchaseRepository);
		
		if (numberOfQuotas == 0) {
			return NumberFormat.getCurrencyInstance().format(BigDecimal.ZERO);
		}
		
		BigDecimal currentAmount = getInvestedAmount(personFiiPurchaseRepository);
		
		BigDecimal average = currentAmount.divide(new BigDecimal(numberOfQuotas),2,RoundingMode.HALF_UP);
		
		return NumberFormat.getCurrencyInstance().format(average);
		
	}
	
	@Transactional
	public int getNumberOfQuotas(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
		
		List<PersonFiiPurchase> purchases = personFiiPurchaseRepository.findByPersonFii(this);
		
		if(CollectionUtils.isEmpty(purchases)) {
			return 0;
		}else {
			
			int amoutOfQuotas = 0;
			
			for(PersonFiiPurchase purchase : purchases) {
				amoutOfQuotas += purchase.getNumberOfQuotas();
			}
			
			return amoutOfQuotas;
			
		}
		
	}
	
	
	@Transactional
	public BigDecimal getInvestedAmount(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
		
		List<PersonFiiPurchase> purchases = personFiiPurchaseRepository.findByPersonFii(this);
		
		if(CollectionUtils.isEmpty(purchases)) {
			return BigDecimal.ZERO;
		}else {
			
			BigDecimal investedAmount = BigDecimal.ZERO;
			
			for(PersonFiiPurchase purchase : purchases) {
				investedAmount = investedAmount.add(purchase.getAmountPerQuota().multiply(new BigDecimal(purchase.getNumberOfQuotas())));
			}
			
			return investedAmount;
			
		}
		
	}
	
	@Transactional
	public String getInvestedAmountFormatted(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
			
		return NumberFormat.getCurrencyInstance().format(getInvestedAmount(personFiiPurchaseRepository));
	
	}
	
	@Transactional
	public String getCurrentAmountFormatted(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
		
		return NumberFormat.getCurrencyInstance().format(getCurrentAmount(personFiiPurchaseRepository));
		
	}
	
	@Transactional
	public BigDecimal getCurrentAmount(PersonFiiPurchaseRepository personFiiPurchaseRepository) {
		
		List<PersonFiiPurchase> purchases = personFiiPurchaseRepository.findByPersonFii(this);
		
		if(CollectionUtils.isEmpty(purchases)) {
			return BigDecimal.ZERO;
		}else {
			
			BigDecimal investedAmount = BigDecimal.ZERO;
			
			for(PersonFiiPurchase purchase : purchases) {
				investedAmount = investedAmount.add(fii.getLastPrice().multiply(new BigDecimal(purchase.getNumberOfQuotas())));
			}
			
			return investedAmount;
			
		}
		
	}
	
	@Transactional
	public String getReceivedDebtsFormatted(PersonFiiDividendRepository personFiiDividendRepository) {
		
		List<PersonFiiDividend> receivedDividends = personFiiDividendRepository.findByPersonFii(this);
		
		if(CollectionUtils.isEmpty(receivedDividends)) {
			return NumberFormat.getCurrencyInstance().format(BigDecimal.ZERO);
		}else {
			
			BigDecimal investedAmount = BigDecimal.ZERO;
			
			for(PersonFiiDividend receivedDividend : receivedDividends) {
				investedAmount = investedAmount.add(receivedDividend.getAmountPerQuota().multiply(new BigDecimal(receivedDividend.getNumberOfQuotas())));
			}
			
			return NumberFormat.getCurrencyInstance().format(investedAmount);
			
		}
		
	}
	
	
	
	
	
}
