package com.Blog.App.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.Entites.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	
	Optional<UserEntity> findByuserEmail(String email);
	
}
