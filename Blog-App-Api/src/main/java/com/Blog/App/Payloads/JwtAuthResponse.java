package com.Blog.App.Payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {
	private String Token;
	private UserDto user; 
}
