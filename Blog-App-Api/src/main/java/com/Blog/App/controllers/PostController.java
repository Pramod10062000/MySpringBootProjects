package com.Blog.App.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Blog.App.Payloads.ApiResponse;
import com.Blog.App.Payloads.PostDto;
import com.Blog.App.Payloads.PostResponse;
import com.Blog.App.services.FileService;
import com.Blog.App.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
	
public class PostController {
	
	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;
	
	
	@Value("${project.image}")
	private String path;
//	http://localhost:5000/api/user/1/category/6/posts
	@PostMapping("/user/{userID}/category/{catId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userID,@PathVariable Integer catId ){
		PostDto dto=this.postService.create(postDto, userID, catId);
		return new ResponseEntity<PostDto>(dto,HttpStatus.CREATED);
	}
	
	//getByUser -Post
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer userId){
		List<PostDto>postMadeByUser=this.postService.getPostMadeByUser(userId);
		return new ResponseEntity<List<PostDto>>(postMadeByUser,HttpStatus.OK);
	}
//	getByCategory -Post
	@GetMapping("/category/{catId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable("catId") Integer catId){
		List<PostDto>postMadeByUserByCategory=this.postService.getPostsByCategory(catId);
		return new ResponseEntity<List<PostDto>>(postMadeByUserByCategory,HttpStatus.OK);
	}
	
	//getAllPost with Pagination
	@GetMapping("/Allposts")
	public ResponseEntity<PostResponse>getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue = "3",required = false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue = "postId" ,required = false) String sortBy,
			@RequestParam(value="sortDir",defaultValue = "asc",required = false)String sortDir
			){
		PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
	return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//getPostById
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto>getByPostId(@PathVariable Integer postId){
		PostDto allpostDto=this.postService.getPostById(postId);
		
	return new ResponseEntity<PostDto>(allpostDto,HttpStatus.OK);
	}
	

	//update post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId)
	{
		 PostDto postdto=this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(postdto,HttpStatus.OK);
	}
	

	//delete post
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
	{
		 this.postService.deletePost(postId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post is deleted",true),HttpStatus.OK);
	}
	
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
		List<PostDto> postDto=this.postService.searchByKeyword(keywords);
		
		return new  ResponseEntity<List<PostDto>>(postDto,HttpStatus.OK);
		
	}
	
	//Post Image Upload
	
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage (
			@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId
			)throws IOException{
		PostDto allpostDto=this.postService.getPostById(postId);
		String uploadImage = this.fileService.uploadImage(path, image);
		allpostDto.setImageName(uploadImage);
		PostDto updaqtedPost=this.postService.updatePost(allpostDto, postId);
		return new ResponseEntity<PostDto>(updaqtedPost,HttpStatus.OK);
		
	}
	
	
	
	//handler to serve image or get image based on the name of image
	
	@GetMapping(value = "post/image/{ImageName}",produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("ImageName")String ImageName,
			HttpServletResponse response
			) throws IOException{
		InputStream resource = this.fileService.getResource(path, ImageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	
	


	}

