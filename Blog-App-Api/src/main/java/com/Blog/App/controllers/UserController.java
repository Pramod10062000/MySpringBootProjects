package com.Blog.App.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.Payloads.ApiResponse;
import com.Blog.App.Payloads.UserDto;
import com.Blog.App.services.UserService;

//import io.swagger.annotations.Api;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", exposedHeaders = "ETag")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//post-create user
	@PostMapping("/")
	public ResponseEntity<UserDto> creatingUser(@Valid @RequestBody UserDto user){
		UserDto createdUser=this.userService.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
		
	}
	//update
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable Integer userId){
		UserDto userE=this.userService.updateUser(user, userId);
		return new ResponseEntity<>(userE,HttpStatus.OK);
		
	}	
	//delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> DeleteHandler(@PathVariable Integer userId) {
		UserDto u =this.userService.getUserById(userId);
		userService.Delete(userId);
		return new ResponseEntity(new ApiResponse("UserDeletedSuccessfully",true),HttpStatus.OK);
	}
	
	//getbyId
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getByUserId(@PathVariable("userId") Integer userId){
		UserDto user= userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);	
	}
	
	//getAll
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getByUserId(){
		List<UserDto> user=userService.getAllUser();
//		List<UserDto> userDto=user.stream().map((e)->this.modelMapper.map(e, UserDto.class)).collect(Collectors.toList());
		return new ResponseEntity<>(user,HttpStatus.OK);
		
	}
	

}
