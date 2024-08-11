package com.aj.blog.services;

import java.util.List;

import com.aj.blog.payload.UserDto;

public interface UserService {

	public UserDto createUser(UserDto userDto);

	public UserDto updateUser(UserDto userDto, int uId);

	public UserDto getUserByUId(int uId);

	public List<UserDto> getAllUsers();

	public void deleteUser(int uId);

}
