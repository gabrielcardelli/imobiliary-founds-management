package project.mundofii.scheduledServices;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import project.mundofii.crawlers.price.UolEconomiaPriceCrawler;
import project.mundofii.crawlers.price.exception.PriceNotExtractedException;
import project.mundofii.domain.Fii;
import project.mundofii.enums.MarketStatus;
import project.mundofii.repository.FiiRepository;
import project.mundofii.services.CheckMarketStatusService;
import project.mundofii.services.UpdateFIIHistoryPriceService;

@Component
public class UpdateFIIHistoryPriceScheduledService {

	// Repositories
	
	private @Autowired FiiRepository fiiRepository;
	
	
	// Services
	
	private @Autowired UpdateFIIHistoryPriceService updateFIIHistoryPriceService;
	
	private @Autowired CheckMarketStatusService checkMarketStatusService;
	
	
    @Scheduled(fixedRate = 36000)
    public void execute() throws IOException, PriceNotExtractedException {
       
    	//if(marketIsOpen()) {
    	
	    	Iterable<Fii> fiis = fiiRepository.findAll();
	    	
	    	for (Fii fii : fiis) {
	    		
	    		Thread thread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							
							BigDecimal price = new UolEconomiaPriceCrawler(fii.getName()).crawl();
	
							updateFIIHistoryPriceService.execute(fii.getId(), price);
							
						} catch (IOException e) {
							e.printStackTrace();
						} catch (PriceNotExtractedException e) {
							e.printStackTrace();
						}
						
					}
				});
	    		
	    		thread.setName("HP - " + fii.getName());
	    		
	    		thread.start();
	    		
	    		
	    	}
	    	
    	//}
    	
    }


	private boolean marketIsOpen() {
		return checkMarketStatusService.execute(null).getObject().equals(MarketStatus.OPEN);
	}
	
}
