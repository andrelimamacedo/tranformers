package com.andrelimamacedo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.andrelimamacedo.model.Transformer;
import com.andrelimamacedo.rules.War;
import com.andrelimamacedo.service.TransformerService;

@RestController
public class TransformerController {

	@Autowired
	TransformerService transformerService;
		
	
	/**
	 * Lists all transformers from database
	 * @return
	 */
	@GetMapping("/all")
	public List<Transformer> getAll(){
		return transformerService.getTransformers();
	}
	
	/**
	 * Creates or update transformers records in database.For new transformers id attribute  MUST BE NULL. 
	 * A transformer with race = 0 is an decepticon and with race = 1 is an autobot
	 * @param transformer
	 * @return
	 */	
	@RequestMapping(value = "/saveTransformer",method = RequestMethod.POST ,
			produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)	
	public String saveTransformer(@RequestBody Transformer transformer) {		
		
		try {
		
			transformerService.saveTransformer(transformer);
		
		}catch(Exception e ) {
			return "Transformer could not be saved.";
		}
		
		return "Transformer " + transformer.getName() + " ready for battle!!."	;
		
	}
	
	/**
	 * Deletes a tranformer based on the id parameter given
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteTransformer/{id}",method = RequestMethod.DELETE , produces = MediaType.TEXT_PLAIN_VALUE)
	public String deleteTransformer(@PathVariable("id")Long id ) {
		
		try{
			
			transformerService.delete(id);
			
		}catch(Exception e) {
			return "Failure to exterminate transformer!";
		}
		return "Transformer vanished from this planet!";
	}

	/**
	 * Starts a war between decepticons and autobots and returns the results
	 * @param id
	 * @return number of battles + Winner team + score + name and number of survivors from the loser team
	 */
	@RequestMapping(value = "/war",method = RequestMethod.GET ,	produces = MediaType.TEXT_PLAIN_VALUE)	
	public String war(@RequestParam List<Long> id) {

		War war = transformerService.startWar(id);
		war.fight();
		return war.getWinner();
	
	}
		
	public String getApplicationName() {		
		return "Transformers War";
	}
}
