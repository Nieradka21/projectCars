package com.project.cars.controller;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cars.model.Users;
import com.project.cars.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UsersController {

	@Autowired
	UserService userService;

	@PostMapping("/email")
	public ResponseEntity<String> enviaEmail(@RequestBody Users users) throws MessagingException, IOException {
		// return ResponseEntity.ok().build();
		if (userService.validaEmail(users.getEmail())) {
			userService.enviarEmail(users);
			return ResponseEntity.ok("Email enviado com sucesso!");
		}
		return ResponseEntity.notFound().build();

	}

	@PostMapping("/teste")
	public List<Users> teste(@RequestBody Users users) throws MessagingException, IOException {

		return userService.teste(users);
	}

	@PutMapping("/reset")
	public ResponseEntity<?> resetarSenha(@RequestBody Users users) {

		return userService.resetSenha(users);
				
					

	}

}
