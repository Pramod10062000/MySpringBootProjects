 package com.Blog.App;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.Blog.App.Entites.Role;
import com.Blog.App.repositories.RoleRepo;



@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner{
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
	
		System.out.println("hello");

		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	

	
	
	@Override
	public void run(String... args) throws Exception {
		try {
			Role r1= new Role();
			r1.setId(501);
			r1.setName("ROLE_ADMIN");
			
			
			Role r2 = new Role();
			r2.setId(502);
			r2.setName("ROLE_USER");
			
			List<Role> roles=List.of(r1,r2);
			this.roleRepo.saveAll(roles);
		}catch (Exception e) {
			// TODO: handle exception
		}

		

	}
//    @Bean
//    ModelMapper modelMapper() {
//		
//		return new ModelMapper();
//	}
}
