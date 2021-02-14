package com.project.cars.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.project.cars.dto.UserDTO;
import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;
import com.project.cars.security.jwt.config.JwtTokenUtil;
import com.project.cars.sendEmail.Email;

import io.jsonwebtoken.ExpiredJwtException;

@CrossOrigin(origins = "*")
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	Email email;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public void enviarEmail(Users users) throws MessagingException, IOException {

		Users us = userRepository.findByEmail(users.getEmail());

		final UserDetails userDetails = new User(us.getEmail(), us.getSenha(), new ArrayList<>());
		String token = JwtTokenUtil.createToken(userDetails);
		userRepository.gerarCodigo(token, us.getEmail());
		email.sendEmailWithAttachment(us.getEmail());
	}

	public List<Users> teste(Users users) {

		return userRepository.findAll();

	}

	public boolean validaEmail(String email) {
		Users name = userRepository.findByEmail(email);

		if (name != null) {
			return true;
		}
		return false;
	}

	public ResponseEntity<?> resetSenha(Users users) {

		try {
			users.setEmail(JwtTokenUtil.getLogin(users.getToken()));

			users.setSenha(bCryptPasswordEncoder().encode(users.getSenha()));

			return ResponseEntity.ok(userRepository.resetPassword(users.getSenha(), users.getEmail()));

		} catch (ExpiredJwtException e) {
			return new ResponseEntity<>("Token is invalid", HttpStatus.BAD_REQUEST);
		}

	}
}
