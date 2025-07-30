package com.example.demo.services;
import java.util.List;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.payloads.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer id) ;
	
	UserDto getUserById(int userId) ;
	
	List<UserDto> getAllUser();
	
	void deleteUser(int Id);
	
	
}