package com.example.demo.impl;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.Repositories.UserRepo;
import com.example.demo.models.User;
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = this.userRepo.findByName(username)
				.orElseThrow(()-> new UsernameNotFoundException(username));
		return user;
	}

	}
	
	


