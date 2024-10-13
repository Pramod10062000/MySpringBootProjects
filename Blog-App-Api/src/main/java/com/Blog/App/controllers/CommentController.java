package com.Blog.App.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog.App.Payloads.ApiResponse;
import com.Blog.App.Payloads.CommentDto;
import com.Blog.App.services.CommentService;

//import io.swagger.annotations.Api;

//import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*", exposedHeaders = "ETag")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDto> createCommemnt(@RequestBody CommentDto comment,@PathVariable Integer postId){
		CommentDto commentSaved = this.commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDto>(commentSaved,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteCommemnt(@PathVariable Integer commentId){
		
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("CommentDeleted",true),HttpStatus.OK);
		
	}
	
}
