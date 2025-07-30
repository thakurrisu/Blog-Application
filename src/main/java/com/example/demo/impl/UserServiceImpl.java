package com.example.demo.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Repositories.UserRepo;

//@Author
//Rishikesh Thakur

import com.example.demo.models.User;
import com.example.demo.payloads.UserDto;
import com.example.demo.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user = userDtoToUser(userDto);
		this.userRepo.save(user);
		UserDto savedUserDto = userToDto(user);
		return savedUserDto;
	}

	@Override
	public UserDto updateUser(UserDto user , Integer userId) {
		// TODO Auto-generated method stub
		
        User userToUpdate = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "Id",userId));
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setId(userId);
        userToUpdate.setName(user.getName());
        userToUpdate.setPassword(user.getPassword());
        UserDto updatedUserDto = userToDto(userToUpdate);
        User updatedUser = this.userRepo.save(userToUpdate);
		return updatedUserDto;
	}

	@Override
	public UserDto getUserById(int userId)  {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "Id",userId));// (userId);
		UserDto userDto = this.userToDto(user);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = this.userRepo.findAll();
		List<UserDto> userDtoList = userList.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtoList;
	}

	@Override
	public void deleteUser(int Id) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(Id).orElseThrow(()->new ResourceNotFoundException("Category","category Id",Id));
		this.userRepo.delete(user);

	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		return userDto;
	}

	private User userDtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
		return user;

	}

}
