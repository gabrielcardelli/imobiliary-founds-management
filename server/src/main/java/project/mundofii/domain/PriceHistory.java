package project.mundofii.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document
public class PriceHistory {
	
	@Id
	@Getter
	private String id;
	
	@Getter @Setter
	private BigDecimal price;
	
	@Getter @Setter
	private Calendar date;
	
	@DBRef
	@Getter @Setter
	private Fii fii;

}
