package com.project.cars.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.cars;
import com.project.cars.services.CarsService;

@RestController
@RequestMapping("/")
public class CarsController {
	@Autowired
	private CarsService service = new CarsService();

	@GetMapping
	public ResponseEntity<List<carsDTO>> getCars() {
		return ResponseEntity.ok(service.getCars());
		// return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable("id") Long id) {
		Optional<carsDTO> car = service.getCarsById(id);

		return car.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

//		return car.isPresent() ? ResponseEntity.ok(car.get()) : ResponseEntity.notFound().build();
		/*
		 * if (car.isPresent()) {
		 * 
		 * return ResponseEntity.ok(car.get()); } else { return
		 * ResponseEntity.notFound().build(); }
		 */

	}

	@GetMapping("/type/{type}")
	public ResponseEntity<?> getType(@PathVariable("type") String type) {
		List<carsDTO> car = service.getCarsByType(type);
		return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);

	}

	@PostMapping
	public cars saveCar(@RequestBody cars car) {
		return service.save(car);
	}

	@PutMapping
	public cars UpCar(@RequestBody cars car) {
		return service.save(car);
	}

	@DeleteMapping("/{id}")
	public cars DelCar(@PathVariable(value = "id") long id) {
		return service.deleteById(id);
	}
}
