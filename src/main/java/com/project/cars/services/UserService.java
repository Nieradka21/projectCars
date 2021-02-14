package com.project.cars.services;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.project.cars.dto.UserDTO;
import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;
import com.project.cars.security.jwt.config.JwtTokenUtil;
import com.project.cars.sendEmail.Email;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@Autowired
	Email email;

	public void enviarEmail(Users users) throws MessagingException, IOException {

		Users us = userRepository.findByEmail(users.getEmail());

		final UserDetails userDetails = new User(us.getEmail(), us.getSenha(), new ArrayList<>());
		String token = JwtTokenUtil.createToken(userDetails);
		userRepository.gerarCodigo(token, us.getEmail());
		email.sendEmailWithAttachment(us.getEmail());
	}

	public Users teste(Users users) throws MessagingException, IOException {
		return userRepository.findByEmail(users.getEmail());
	}

	public boolean validaEmail(String email) {
		Users name = userRepository.findByEmail(email);

		if (name != null) {
			return true;
		}
		return false;
	}
}
