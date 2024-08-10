package com.aj.blog.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aj.blog.payload.ApiResponse;
import com.aj.blog.payload.UserDto;
import com.aj.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto user = this.userService.createUser(userDto);
		
		return new ResponseEntity<UserDto>(user, HttpStatus.CREATED);
	}
	
	@PutMapping("/user/{uId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int uId)
	{
		UserDto user = this.userService.updateUser(userDto, uId);
		
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> allUsers = this.userService.getAllUsers();
		
		return new ResponseEntity<List<UserDto>> (allUsers, HttpStatus.OK);
	}
	
	@GetMapping("/user/{uId}")
	public ResponseEntity<UserDto> getUserByUId(@PathVariable int uId)
	{
		UserDto userByUId = this.userService.getUserByUId(uId);
		
		return new ResponseEntity<UserDto> (userByUId, HttpStatus.OK);
	}
	
	@DeleteMapping("/user/{uId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int uId)
	{
		this.userService.deleteUser(uId);
		
		return new ResponseEntity<ApiResponse> (new ApiResponse("User Deleted Successfully..!", true), HttpStatus.OK);
	}
	
}
