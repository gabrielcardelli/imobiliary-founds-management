package project.mundofii.services;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import project.mundofii.enums.MarketStatus;

@Service
public class CheckMarketStatusService implements AbstractService<Void, MarketStatus>{

	@Override
	public MarketStatus businessRule(Void t) {

		if(Calendar.getInstance().after(tenAM()) && Calendar.getInstance().before(fifteenPM())) {
			return MarketStatus.OPEN;
		}else {
			return MarketStatus.CLOSED;
		}

	}

	@Override
	public ServiceValidationResult validation(Void t) {
		return new ServiceValidationResult();
	}
	
	
	public Calendar tenAM() {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 10);
		c.set(Calendar.MINUTE, 0);
		
		return c;
		
	}
	
	public Calendar fifteenPM() {
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 17);
		c.set(Calendar.MINUTE, 0);
		
		return c;
		
	}

}
