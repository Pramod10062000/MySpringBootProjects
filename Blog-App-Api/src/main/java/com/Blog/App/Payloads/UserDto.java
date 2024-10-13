package com.Blog.App.Payloads;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 characters !!")
	private String userName;
	
	
	@Email(message = "Email address is not valid !!")
	@NotEmpty(message = "Email is required !!")
	private String userEmail;
	
	@NotEmpty
	@Size(min = 3, max = 300, message = "Password must be min of 3 chars and max of 300 chars !!")
	private String password;
	
	@NotEmpty(message = "Enter your Bio Bio cannot be Empty")
	private String about;
	
	private Set<RoleDto> roles=new HashSet<>();

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public String setPassword(String password) {
		return this.password=password;
	}


}
