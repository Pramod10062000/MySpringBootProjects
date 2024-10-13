package com.Blog.App.services;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import com.Blog.App.Entites.UserEntity;
import com.Blog.App.Payloads.UserDto;


public interface UserService {
	UserDto registerUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto createAdminUser(UserDto user);
	UserDto updateUser(UserDto user,Integer id);
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	void Delete(Integer id);
}
