package com.project.cars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.project.cars.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByLogin(String login);

	Users findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query(value = "update Users u set u.token = :token where u.email = :email")
	Integer gerarCodigo(@Param("token") String temp, @Param("email") String email);


}
