package com.project.cars.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;

@Service(value = "userDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = usersRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return user;
		
	}

}
