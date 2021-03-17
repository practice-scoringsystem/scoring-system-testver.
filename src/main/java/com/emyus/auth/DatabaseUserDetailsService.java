package com.emyus.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		System.out.println(id);
		if (isEmpty(id)) {
			System.out.println("emptyの中");
			throw new UsernameNotFoundException("ユーザーIDを入力してください");
		}
		System.out.println("emptyの外");
		return userRepository.identifyUser(id);
	}

	private boolean isEmpty(String id) {
		return false;
	}

}
