package com.Blog.App.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.Blog.App.Payloads.CategoryDto;
import com.Blog.App.services.CategoryService;


//import jakarta.validation.Valid;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*", exposedHeaders = "ETag")

public class CategoryController {
	
	@Autowired
	private CategoryService catService;

	
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto cat){
	
		CategoryDto created=this.catService.createCategory(cat);
		return new ResponseEntity<CategoryDto>(created,HttpStatus.CREATED);
		
	}

	//update	
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>updatingCategory(@Valid @RequestBody CategoryDto catdto , 
			@PathVariable("categoryId") Integer categoryId){
		CategoryDto dto=this.catService.updateCategory(catdto, categoryId);
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deleteHandler(@PathVariable("catId") Integer catId){
		this.catService.DeleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted ",true),HttpStatus.OK);
	}
	
	//getById
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto>getById(@PathVariable Integer catId){
		CategoryDto dto=this.catService.get(catId);
		return new ResponseEntity<CategoryDto>(dto,HttpStatus.OK);
	}
	
	//getAll
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>>getAll(){
		List<CategoryDto> dto=this.catService.GetAllCategory();
//		System.out.println(dto);
		return new ResponseEntity<List<CategoryDto>>(dto,HttpStatus.OK);
	}
	

}
