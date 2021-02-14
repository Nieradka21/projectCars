package com.project.cars.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.Cars;
import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;
import com.project.cars.security.jwt.config.JwtTokenUtil;
//import com.project.cars.sendEmail.Email;
import com.project.cars.services.CarsService;

@RestController
@RequestMapping("/api")
public class CarsController {
	@Autowired
	private CarsService service = new CarsService();

	@Autowired
	private JwtTokenUtil jwt;

	
	@GetMapping
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public ResponseEntity<List<carsDTO>> getCars() {
		return ResponseEntity.ok(service.getCars());
		// return new ResponseEntity<>(service.getCars(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getId(@PathVariable("id") Long id) {

		return ResponseEntity.ok(service.getCarsById(id));

	}

	@GetMapping("/type/{type}")
	public ResponseEntity<Page<carsDTO>> getType(@PathVariable("type") String type,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "4") Integer size) {
		Page<carsDTO> car = service.getCarsByType(type, PageRequest.of(page, size));
		return car.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(car);

	}

	@PostMapping
	public ResponseEntity<?> saveCar(@RequestBody Cars car) {

		carsDTO c = service.save(car);
		URI location = getUri(c.getId());
		return ResponseEntity.created(location).build();
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping
	public ResponseEntity<?> UpCar(@RequestBody Cars car) {

		return car.getId() != null ? ResponseEntity.ok(service.UpCar(car)) : ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	public ResponseEntity<?> DelCar(@PathVariable(value = "id") Long id) {

		service.deleteById(id);

		return ResponseEntity.ok().build();

	}

	

//	@GetMapping("/teste")
//	public Users teste() {
//
//		return userRepository.findByEmail("admin@gmail.com");
//	}
}
