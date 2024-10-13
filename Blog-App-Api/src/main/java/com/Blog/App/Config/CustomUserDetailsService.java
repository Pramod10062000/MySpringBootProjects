package com.Blog.App.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Blog.App.Entites.UserEntity;
import com.Blog.App.Exceptions.ResourceNotFoundException;
import com.Blog.App.repositories.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {
	
		@Autowired
		private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user data from data base using userName where userName is Email
	UserEntity user= this.userRepository.findByuserEmail(username).orElseThrow(()->new ResourceNotFoundException("username", "email:"+username, 0));
		return user;
	}

}
