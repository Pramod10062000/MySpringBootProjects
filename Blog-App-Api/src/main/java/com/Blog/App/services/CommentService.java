package com.Blog.App.services;

import com.Blog.App.Payloads.CommentDto;

public interface CommentService {

	
	CommentDto createComment(CommentDto cooment,Integer postId);
	void deleteComment(Integer CommentId);
}
