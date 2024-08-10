package com.aj.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.blog.entities.User;
import com.aj.blog.exceptions.ResourceNotFoundException;
import com.aj.blog.payload.UserDto;
import com.aj.blog.repositories.UserRepository;
import com.aj.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = mapper.map(userDto, User.class);
		
		User savedUser = this.userRepository.save(user);
		
		UserDto dto = mapper.map(savedUser, UserDto.class);
		
		return dto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, int uId) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));	
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser =this.userRepository.save(user);
		
		UserDto dto = mapper.map(updatedUser, UserDto.class);
		
		return dto;
	}

	@Override
	public UserDto getUserByUId(int uId) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		UserDto userDto = mapper.map(user, UserDto.class);
		
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> list = this.userRepository.findAll();
		
		List<UserDto> dtoList = list.stream().map((user)-> this.mapper.map(user, UserDto.class)).collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public void deleteUser(int uId) {
		
		User user = this.userRepository.findById(uId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", uId));
		
		this.userRepository.delete(user);
	}

}
