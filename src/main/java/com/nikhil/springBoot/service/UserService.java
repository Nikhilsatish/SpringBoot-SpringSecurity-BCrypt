package com.nikhil.springBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nikhil.springBoot.dto.UserDTO;
import com.nikhil.springBoot.entity.User;
import com.nikhil.springBoot.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(UserDTO user) {
		//encode the password
		User saveUser = new User(user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getRole(), user.getFullname());
		return userRepository.save(saveUser);
	}

}
