package com.Blog.App.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Blog.App.Entites.Catogories;
import com.Blog.App.Payloads.CategoryDto;



public interface CategoryService {
	
     //	create
	 public CategoryDto createCategory(CategoryDto cat);
	 
	//update
	 public CategoryDto updateCategory(CategoryDto cat,Integer id);
	
	//delete
	 public void DeleteCategory(Integer id);
	
	
	//get 
	 public CategoryDto get(Integer id);
		
	 
	// get all
	 public List<CategoryDto> GetAllCategory();
	
}
