package com.emyus.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emyus.auth.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(id);
		if (user == null) {
			throw new UsernameNotFoundException(id + "is not found");
		} else {
			System.out.println("emptyの外" + id);
			System.out.println("emptyの外");
			return user;
		}
	}

}
