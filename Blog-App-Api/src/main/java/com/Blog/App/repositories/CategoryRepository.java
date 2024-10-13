package com.Blog.App.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog.App.Entites.Catogories;
import com.Blog.App.Payloads.CategoryDto;

public interface CategoryRepository extends JpaRepository<Catogories, Integer> {

//	Catogories save(CategoryDto cat);
	

}
