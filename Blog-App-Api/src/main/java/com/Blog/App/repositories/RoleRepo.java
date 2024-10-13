package com.Blog.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.Entites.Role;

public interface RoleRepo  extends JpaRepository<Role,Integer>{
	
	

}
