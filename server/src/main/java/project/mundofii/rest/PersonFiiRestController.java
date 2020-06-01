package project.mundofii.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.mundofii.converter.PersonFiiConverter;
import project.mundofii.converter.PersonFiiDividendConverter;
import project.mundofii.converter.PersonFiiPurchaseConverter;
import project.mundofii.domain.PersonFii;
import project.mundofii.domain.PersonFiiDividend;
import project.mundofii.domain.PersonFiiPurchase;
import project.mundofii.dtos.PersonFiiDTO;
import project.mundofii.dtos.PurchaseDTO;
import project.mundofii.dtos.ReceivedDividendDTO;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.PersonFiiDividendRepository;
import project.mundofii.repository.PersonFiiPurchaseRepository;
import project.mundofii.repository.PersonFiiRepository;
import project.mundofii.repository.PersonRepository;
import project.mundofii.services.AddFiiToPersonService;
import project.mundofii.services.AddPersonFiiDividendService;
import project.mundofii.services.AddPersonFiiPurchaseService;
import project.mundofii.services.DeleteFiiOfPersonService;

/**
 * 
 * This Rest Interface is responsible to expose operations that involve the fiis of person.
 *
 */
@RestController
@CrossOrigin
public class PersonFiiRestController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// Services
	
	private @Autowired AddFiiToPersonService addFiiToPersonWalletService;
	
	private @Autowired AddPersonFiiDividendService addFiiWalletDividendService;
	
	private @Autowired AddPersonFiiPurchaseService addPurchaseToPersonFiiService;
	
	private @Autowired DeleteFiiOfPersonService deleteFiiOfPersonService;
	
	
	// Repositories
	
	private @Autowired PersonFiiRepository personFiiRepository;
	
	private @Autowired PersonRepository personRepository;
	
	private @Autowired FiiRepository fiiRepository;
	
	private @Autowired PersonFiiPurchaseRepository personFiiPurchaseRepository;
	
	private @Autowired PersonFiiDividendRepository personFiiDividendRepository;
	
	
	// Converter
	
	private @Autowired PersonFiiConverter personFiiConverter;
	
	private @Autowired PersonFiiPurchaseConverter personFiiPurchaseConverter;
	
	private @Autowired PersonFiiDividendConverter personFiiDividendConverter;
	
	
	// Rest Methods
	
	@RequestMapping(path="/person/{personId}/fiis",method=RequestMethod.POST)
	public ResponseEntity<Void> addFiiToPerson(PersonFiiDTO dto){
		
		log.info("Adding FII " + dto.getFiiId() + " to person " + dto.getPersonId());
		
		addFiiToPersonWalletService.execute(dto);
		
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis",method=RequestMethod.GET)
	public ResponseEntity<List<PersonFiiDTO>> listPersonFiis(@PathVariable String personId){
		
		log.info("Listing FIIs of person " + personId);
		
		List<PersonFii> fiis = personFiiRepository.findByPerson(personRepository.findById(personId).get());
		
		log.info("Total FIIs: " + fiis.size());
		
		return ResponseEntity.ok(personFiiConverter.convertToDTOList(fiis.iterator()));
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}",method=RequestMethod.GET)
	public ResponseEntity<PersonFiiDTO> listPersonFiis(@PathVariable String personId, @PathVariable String fiiId){
		
		log.info("Listing FIIs of person " + personId);
		
		PersonFii personFii = personFiiRepository.findByPersonAndFii(personRepository.findById(personId).get(), fiiRepository.findById(fiiId).get());
		
		if(personFii != null) {
			return ResponseEntity.ok(personFiiConverter.convertToDTO(personFii));
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteFiiOfPerson(@PathVariable String personId, @PathVariable String fiiId){
			
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("personId", personId);
		params.put("fiiId", fiiId);
		
		deleteFiiOfPersonService.execute(params);
		
		return ResponseEntity.ok().build();
		
	}
	
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}/dividends",method=RequestMethod.POST)
	public ResponseEntity<List<PersonFiiDTO>> addDividendToFiiWallet(ReceivedDividendDTO dto){
		
		log.info("Adding dividend to fii" + dto.getFiiId() + " of person " + dto.getPersonId());
		
		addFiiWalletDividendService.execute(dto);
		
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}/purchases",method=RequestMethod.POST)
	public ResponseEntity<List<PersonFiiDTO>> addQuotaToFiiWallet(PurchaseDTO dto){
		
		log.info("Adding pruchase to fii" + dto.getFiiId() + " of person " + dto.getPersonId());
		
		this.addPurchaseToPersonFiiService.execute(dto);
		
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}/purchases",method=RequestMethod.GET)
	public ResponseEntity<List<PurchaseDTO>> listPurchasesOfPersonFii(@PathVariable String personId, @PathVariable String fiiId){
		
		PersonFii personFii = personFiiRepository.findByPersonAndFii(personRepository.findById(personId).get(), fiiRepository.findById(fiiId).get());
		
		List<PersonFiiPurchase> purchases = personFiiPurchaseRepository.findByPersonFii(personFii);
		
		return ResponseEntity.ok(personFiiPurchaseConverter.convertToDTOList(purchases.iterator()));
		
	}
	
	@RequestMapping(path="/person/{personId}/fiis/{fiiId}/dividends",method=RequestMethod.GET)
	public ResponseEntity<List<ReceivedDividendDTO>> listDividendsOfPersonFii(@PathVariable String personId, @PathVariable String fiiId){
		
		PersonFii personFii = personFiiRepository.findByPersonAndFii(personRepository.findById(personId).get(), fiiRepository.findById(fiiId).get());
		
		List<PersonFiiDividend> dividends = personFiiDividendRepository.findByPersonFii(personFii);
		
		return ResponseEntity.ok(personFiiDividendConverter.convertToDTOList(dividends.iterator()));
		
	}
	
	
	
}
