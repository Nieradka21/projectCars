package com.project.cars.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.cars.dto.carsDTO;
import com.project.cars.exception.ObjectNotFoundException;
import com.project.cars.model.Cars;
import com.project.cars.repository.CarsRepository;

import org.springframework.util.Assert;

@Service
public class CarsService {

	@Autowired
	private CarsRepository rep;

	public List<carsDTO> getCars() {

		return rep.findAll().stream().map(carsDTO::create).collect(Collectors.toList());
	}

	public carsDTO getCarsById(Long id) {
		Optional<Cars> car = rep.findById(id);
		return car.map(carsDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));
	}

	public List<carsDTO> getCarsByType(String type) {
		return rep.findByType(type).stream().map(carsDTO::create).collect(Collectors.toList());
	}

	public carsDTO save(Cars car) {
		Assert.isNull(car.getId(), "Não foi possível inserir o registro");
		return carsDTO.create(rep.save(car));

	}

	public carsDTO UpCar(Cars car) {
		Assert.notNull(car.getId(), "Não foi possível atualizar o registro");
		return carsDTO.create(rep.save(car));

	}

	public void deleteById(Long id) {

		rep.deleteById(id);

	}

	/*
	 * public List<cars> getCarsFake() { List<cars> car = new ArrayList<>();
	 * 
	 * car.add(new cars(1L, "Fusca")); car.add(new cars(2L, "Fiesta")); car.add(new
	 * cars(3L, "Celta"));
	 * 
	 * return car; }
	 */

}
