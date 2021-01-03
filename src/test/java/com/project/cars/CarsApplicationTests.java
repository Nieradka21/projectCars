package com.project.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.cars;
import com.project.cars.services.CarsService;

@SpringBootTest
class CarsApplicationTests {

	@Autowired
	private CarsService service;

	@Test
	void contextLoads() {
		cars car = new cars();
		car.setName("Ferrari");
		car.setType("esportivos");
		carsDTO c = service.save(car);

		assertNotNull(c);
		Long id = c.getId();
		assertNotNull(id);
//Buscar o objeto
		 service.getCarsById(id);
		
		

		assertEquals("Ferrari", c.getName());
		assertEquals("esportivos", c.getType());
//deletar o objeto
		service.deleteById(id);
//verificar se deletou 
		
	}

}
