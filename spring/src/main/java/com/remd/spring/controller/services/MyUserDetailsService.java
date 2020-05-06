package com.remd.spring.controller.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.remd.spring.bean.MyUserDetails;
import com.remd.spring.bean.User;
import com.remd.spring.repository.UserRepository;

/*
 * PUT ALL POSSIBLE AUTHENTICATION HERE
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	/* Return user details */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByUserName(userName); //Return a new a instance of UserDetails
		
		//Return UsernameNotFoundException if Null or empty
		user.orElseThrow(() -> new UsernameNotFoundException("Invalid username: " + userName));
		
		return user.map(MyUserDetails::new).get();
	}

}
