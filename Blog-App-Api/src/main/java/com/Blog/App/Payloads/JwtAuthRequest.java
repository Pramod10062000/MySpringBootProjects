package com.Blog.App.Payloads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	
	private String username;
	private String password;

}
