 package com.project.cars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

	List<Cars> findByType(String type);

	
}
