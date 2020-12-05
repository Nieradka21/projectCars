 package com.project.cars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.cars;

public interface CarsRepository extends JpaRepository<cars, Long> {

	List<cars> findByType(String type);

	cars deleteById(long id);
}
