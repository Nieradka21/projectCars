package com.project.cars.security.jwt.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByLogin(username);
		if (user.getLogin().equals(username)) {
			return new User(user.getLogin(), user.getSenha(), new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		//return new User("user", "$2a$10$6gqEznve6W7BfyfqKUkpR.AyeSOwM4j5YEJabqcOAdYpF6E.iIWDW", new ArrayList<>());
	}

}
