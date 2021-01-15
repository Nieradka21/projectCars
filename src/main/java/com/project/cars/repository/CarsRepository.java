 package com.project.cars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.cars.dto.carsDTO;
import com.project.cars.model.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

	Page<Cars> findByType(String type,Pageable pageable);

	
}
