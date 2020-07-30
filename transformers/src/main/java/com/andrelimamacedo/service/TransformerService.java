package com.andrelimamacedo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrelimamacedo.model.Transformer;
import com.andrelimamacedo.repository.TransformerRepository;
import com.andrelimamacedo.rules.War;

@Service
public class TransformerService {

	@Autowired
	TransformerRepository transformerRepository;
	
	public List<Transformer> getTransformers(){
		
		return (List<Transformer>) transformerRepository.findAll();
	}
	
	public Transformer saveTransformer(Transformer t) {
		return transformerRepository.save(t);
	}
	
	public Transformer findById(Long id) {
		return transformerRepository.findById(id).orElse(null);
	}
	
	public void delete(Long id) {
		transformerRepository.deleteById(id);
	}
	
	/**
	 * Starts a War, putting transformers on correspondents teams according to their race
	 * @param ids
	 * @return
	 */
	public War startWar(List<Long> ids) {
		War war = new War();
		List<Transformer> autobots = new ArrayList<Transformer>();
		List<Transformer>  decepticons = new ArrayList<Transformer>();
		Transformer transformer;
		for (Long id : ids) {
			transformer = new Transformer();
			transformer = findById(id);
			if(transformer != null ) {
				if(transformer.getRace() == 'D') {
					decepticons.add(transformer);
				}
				if(transformer.getRace() == 'A') {
					autobots.add(transformer);
				}
			}
		}
		war.setAutobots(autobots);
		war.setDecepticons(decepticons);
		return war;
	}	

	
}
