package com.Blog.App.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.Entites.Catogories;
import com.Blog.App.Entites.PostEntity;
import com.Blog.App.Entites.UserEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
	
	
	//All of these methods are custom finder Methods
	
	//finds all the post made by the user
	List<PostEntity>findByUser(UserEntity user);
	
	
//Finds all the Posts belonging to the given catogory
	List<PostEntity>findBycategory(Catogories catogories);

// searching
	List<PostEntity> findBypostTitleContaining(String title);
}
