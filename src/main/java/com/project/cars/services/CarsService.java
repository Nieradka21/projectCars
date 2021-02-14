package com.project.cars.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.project.cars.dto.carsDTO;
import com.project.cars.exception.ObjectNotFoundException;
import com.project.cars.model.Cars;
import com.project.cars.repository.CarsRepository;

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

	public Page<carsDTO> getCarsByType(String type, Pageable pageable) {
		Page<Cars> car = rep.findByType(type, pageable);

		return car.map(carsDTO::create);
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


}
