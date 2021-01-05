package com.project.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.cars.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByLogin(String login);
	
}
