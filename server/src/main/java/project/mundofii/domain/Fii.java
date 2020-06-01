package project.mundofii.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.querydsl.core.annotations.QueryEntity;

import lombok.Getter;
import lombok.Setter;

@QueryEntity
@Document
public class Fii {
	
	@Id
	@Getter
	private String id;
	
	@Getter @Setter
	private String name;
	
	@DBRef
	@Getter @Setter
	private Administrator administrator;
	
	@Getter @Setter
	private FiiType type;
	
	@DBRef
	@Getter @Setter
	private Manager manager;
	
	@DBRef
	@Getter @Setter
	private List<PriceHistory> prices;
	
	@Getter @Setter
	private BigDecimal lastPrice;
	
	public String getLastPriceFormatted() {
		
		if(getLastPrice() == null) {
			return null;
		}
		
		return NumberFormat.getCurrencyInstance().format(getLastPrice().doubleValue());
	}

}
