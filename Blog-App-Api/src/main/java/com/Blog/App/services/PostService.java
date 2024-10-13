package com.Blog.App.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Blog.App.Entites.PostEntity;
import com.Blog.App.Entites.UserEntity;
import com.Blog.App.Payloads.PostDto;
import com.Blog.App.Payloads.PostResponse;


public interface PostService {
	
    //create
	 PostDto create(PostDto post, Integer userId ,Integer catId);

	//update
	 PostDto updatePost(PostDto post , int postId);
	
	//delete
	 void deletePost(Integer postId);
	 
	 
	//GET
	 PostDto getPostById(int PostId);
	 
	 
	//getAll with Pagination
	public PostResponse getAllPost(Integer pageNumber,Integer PageSize,String sortBy,String sortDir);

	//get posts by Category
	List<PostDto> getPostsByCategory(Integer catId);

	//getAll post by user
	List<PostDto> getPostMadeByUser(Integer userId);
	
	
	//search Post
	List<PostDto> searchByKeyword(String keyword);
}
