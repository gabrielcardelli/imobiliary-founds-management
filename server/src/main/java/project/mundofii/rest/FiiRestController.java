package project.mundofii.rest;

import java.util.Arrays;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.mundofii.converter.CommentConverter;
import project.mundofii.converter.FiiConverter;
import project.mundofii.domain.FiiType;
import project.mundofii.dtos.CommentDTO;
import project.mundofii.dtos.FiiDTO;
import project.mundofii.repository.CommentRepository;
import project.mundofii.repository.FiiRepository;
import project.mundofii.repository.params.FiiSearchParams;
import project.mundofii.repository.predicates.FiiPredicate;
import project.mundofii.services.AddCommentService;
import project.mundofii.services.AddFiiService;

@RestController
@CrossOrigin
public class FiiRestController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	
	// Repositories
	
	private @Autowired FiiRepository fiiRepository;
	
	private @Autowired CommentRepository commentRepository;
	
	
	//Services
	
	private @Autowired AddCommentService addCommentService;
	
	private @Autowired AddFiiService addFiiService;
	
	
	// Converters
	
	private @Autowired FiiConverter fiiConverter;
	
	private @Autowired CommentConverter commentConverter;
	
	
	// Rest Methods
	
	@RequestMapping(path="/fiis",method=RequestMethod.POST)
	public ResponseEntity<Void> save(FiiDTO fii){
		
		addFiiService.execute(fii);
		
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(path="/fiis",method=RequestMethod.GET)
	public ResponseEntity<List<FiiDTO>> all(FiiSearchParams params){
		
		log.info("Listing fiis by params: " + params);
		
		return ResponseEntity.ok(fiiConverter.convertToDTOList(
				fiiRepository.findAll(FiiPredicate.get(params)).iterator()));
	}
	
	@RequestMapping(path="/fiis/{name}",method=RequestMethod.GET)
	public ResponseEntity<FiiDTO> getByInitials(@PathVariable String name){
		
		log.info("Listing fiis by name: " + name.toUpperCase());
		
		return ResponseEntity.ok(fiiConverter.convertToDTO(fiiRepository.findByName(name.toUpperCase())));
	}
	
	@RequestMapping(path="/fiis/{fiiId}/comments", method=RequestMethod.POST)
	public ResponseEntity<Void> addComment(CommentDTO dto){

		log.info("Adding comment for fii " + dto.getFiiId());
		
		addCommentService.execute(dto);
		
		return ResponseEntity.ok().build();
		
	}
	
	@RequestMapping(path="/fiis/{id}/comments", method=RequestMethod.GET)
	public ResponseEntity<List<CommentDTO>> allComments(@PathVariable String id){
		
		log.info("Listing last ten fii " + id + " comments");
		
		List<CommentDTO> comments = 
				this.commentConverter.convertToDTOList(commentRepository
						.findByFiiOrderByIdDesc(fiiRepository.findById(id).get(),PageRequest.of(0, 10)).iterator());
		
		if(CollectionUtils.isEmpty(comments)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comments);
		
	}
	
	@RequestMapping(path="/fiis/types")
	public ResponseEntity<List<FiiType>> types (){
		
		return ResponseEntity.ok(Arrays.asList(FiiType.values()));
		
	}
	
}
