package com.Blog.App.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.Config.JwtImplementation.JwtTokenHelper;
import com.Blog.App.Exceptions.ApiExceptionHandling;
import com.Blog.App.Payloads.JwtAuthRequest;
import com.Blog.App.Payloads.JwtAuthResponse;
import com.Blog.App.Payloads.UserDto;
import com.Blog.App.services.UserService;

import jakarta.validation.Valid;

//import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/auth/")
public class AuthControllerLoginApi {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMappper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@Valid @RequestBody JwtAuthRequest request) throws Exception{
		System.out.println(request);
		
//		System.out.println(request.getUsername()+""+request.getPassword());
		//calling authenticate() to perform authentication of given data by user
		this.authenticate(request.getUsername(),request.getPassword());
//		if successfull rest of the code will be executed
		//this line will fetch all the details of user from the database with id username etc everything
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
//		System.out.println(userDetails);
		//jwt token generation
		String Token=jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse jwtResponse=new JwtAuthResponse();
		UserDto userDt=this.modelMappper.map(userDetails, UserDto.class);
		jwtResponse.setToken(Token);
		jwtResponse.setUser(userDt);
//		System.out.println(jwtResponse);
		//sending the jwt token inside an object with http status code
		return new ResponseEntity<JwtAuthResponse>(jwtResponse,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(username,password);
		try {
			//responsible for authentication if successfull then fine else exception
			this.authenticationManager.authenticate(usernamePassword);

		}catch (BadCredentialsException e) {
		throw new ApiExceptionHandling("Invalid Username or Password . Please give Correct username or Password");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto user){
		UserDto userSaved=this.userService.registerUser(user);
		return new ResponseEntity<UserDto>(userSaved,HttpStatus.OK);
		
	}
	
	@PostMapping("/registerAdmin")
	public ResponseEntity<UserDto> registerAdmin(@Valid @RequestBody UserDto user){
		UserDto userSaved=this.userService.createAdminUser(user);
		return new ResponseEntity<UserDto>(userSaved,HttpStatus.OK);
	}
	
	
}
