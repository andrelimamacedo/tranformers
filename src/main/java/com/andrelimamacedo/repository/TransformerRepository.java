package com.andrelimamacedo.repository;


import org.springframework.data.repository.CrudRepository;

import com.andrelimamacedo.model.Transformer;

public interface TransformerRepository extends CrudRepository<Transformer,Long>{

	public Transformer findByName(String name);
		
}
