package com.project.cars.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.cars;
import com.project.cars.services.CarsService;

@RestController
@RequestMapping("/api")
public class CarsController {
	@Autowired
	private CarsService service = new CarsService();

	@GetMapping
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity<List<carsDTO>> getCars() {
		return ResponseEntity.ok(service.getCars());
		// return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<carsDTO> getId(@PathVariable("id") Long id) {

		return ResponseEntity.ok(service.getCarsById(id));

	}

	@GetMapping("/type/{type}")
	public ResponseEntity<List<carsDTO>> getType(@PathVariable("type") String type) {
		List<carsDTO> car = service.getCarsByType(type);
		return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);

	}

	@PostMapping
	public ResponseEntity<?> saveCar(@RequestBody cars car) {

		carsDTO c = service.save(car);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping
	public ResponseEntity<?> UpCar(@RequestBody cars car) {

		return car.getId() != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DelCar(@PathVariable(value = "id") Long id) {

		service.deleteById(id);

		return ResponseEntity.ok().build();

	}
}
