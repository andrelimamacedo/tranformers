package com.andrelimamacedo.transformers.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import com.andrelimamacedo.controller.TransformerController;
import com.andrelimamacedo.model.Transformer;
import com.andrelimamacedo.repository.TransformerRepository;
import com.andrelimamacedo.rules.War;
import com.andrelimamacedo.service.TransformerService;



import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = TransformerController.class)
@ActiveProfiles("test")
public class TransformerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TransformerService transformerService;
	
	@MockBean
	private TransformerRepository transformerRepository;
	
	private List<Transformer> transformers;
	
	
	@BeforeEach
	void setUp() {
		transformers = new ArrayList<Transformer>();		
		this.transformers.add(new Transformer(1L,"andre",8,7,6,5,9,3,10,9,'A'));	
		this.transformers.add(new Transformer(2L,"Optimus Prime",8,9,6,5,10,3,10,9,'A'));
		this.transformers.add(new Transformer(3L,"Bumblebee",8,7,6,5,8,3,10,9,'A'));
		this.transformers.add(new Transformer(4L,"Megatron",7,7,6,5,9,4,10,9,'D'));
		this.transformers.add(new Transformer(5L,"Starscream",7,8,6,5,9,4,10,9,'D'));
		this.transformers.add(new Transformer(6L,"Predaking",7,7,6,5,8,4,10,9,'D'));
		this.transformers.add(new Transformer(6L,"Jetstorm",7,7,4,5,9,4,10,9,'D'));
		this.transformers.add(new Transformer(6L,"Bonecrusher",7,10,4,5,9,4,10,9,'D'));
	}
	
	@Test
	void shouldFetchAllTransformers() throws Exception{
				
		given(transformerService.getTransformers()).willReturn(transformers);
				
		this.mockMvc.perform(get("/all"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.size()",is(transformers.size())));
		
	}		
	
	
	@Test
	void applicationName() {
		TransformerController controller = new TransformerController(); //arange
		String  response = controller.getApplicationName(); //act
		assertEquals("Transformers War", response);  //assert
	}
	
	@Test
	void makeWar() throws Exception{
		War war = new War();
		war.setAutobots(transformers.subList(0, 3));
		war.setDecepticons(transformers.subList(3, 8));
		war.fight();
		System.out.println(war.getWinner());
		
	}
}
