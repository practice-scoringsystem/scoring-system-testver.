package com.emyus.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import com.emyus.auth.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userRepository.identifyUser(id);
		if (user == null) {
			throw new UsernameNotFoundException(id + "is not found");
		} else {
			session.setAttribute("user_name", user.getUserName());
			session.setAttribute("user_id", user.getId());
			return user;
		}
	}

}
