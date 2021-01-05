package com.project.cars.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.cars.model.Users;
import com.project.cars.repository.UserRepository;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRep;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = userRep.findByLogin(username);

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		return user;

	}

}
