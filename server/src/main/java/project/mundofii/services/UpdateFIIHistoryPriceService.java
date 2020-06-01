package project.mundofii.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.mundofii.domain.Fii;
import project.mundofii.domain.PriceHistory;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PriceHistoryRepository;

@Service
public class UpdateFIIHistoryPriceService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FiiRepository fiiRepository;
	
	@Autowired
	private PriceHistoryRepository priceHistoryRepository;
	
	@Transactional
	public void execute(String fiiId, BigDecimal price) {
		
		Fii fii = fiiRepository.findById(fiiId).get();
		
		if(fii.getLastPrice() == null || !fii.getLastPrice().equals(price)) {
			
			log.debug("Atualizando preço do FII " + fii.getName() + " de " + fii.getLastPrice() + " para " + price);
			
			fii.setLastPrice(price);
			
			fiiRepository.save(fii);
			
			PriceHistory priceHistory = new PriceHistory();
			priceHistory.setFii(fii);
			priceHistory.setPrice(price);
			priceHistory.setDate(Calendar.getInstance());
			
			priceHistoryRepository.save(priceHistory);
			
		}

	}
	
	
	
}
