package com.project.cars.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.cars;
import com.project.cars.repository.CarsRepository;

@Service
public class CarsService {

	@Autowired
	private CarsRepository rep;

	public List<carsDTO> getCars() {

		return rep.findAll().stream().map(carsDTO::new).collect(Collectors.toList());
	}

	public Optional<carsDTO> getCarsById(Long id) {

		return rep.findById(id).map(carsDTO::new);
	}

	public List<carsDTO> getCarsByType(String type) {
		return rep.findByType(type).stream().map(carsDTO::new).collect(Collectors.toList());
	}

	public cars save(cars car) {
		return rep.save(car);

	}

	public List<cars> getCarsFake() {
		List<cars> car = new ArrayList<>();

		car.add(new cars(1L, "Fusca"));
		car.add(new cars(2L, "Fiesta"));
		car.add(new cars(3L, "Celta"));

		return car;
	}

	public cars deleteById(long id) {
		return rep.deleteById(id);
	}

}
