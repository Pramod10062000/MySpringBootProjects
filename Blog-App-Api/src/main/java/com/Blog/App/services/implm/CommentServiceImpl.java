package com.Blog.App.services.implm;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Blog.App.Entites.CommentEntity;
import com.Blog.App.Entites.PostEntity;
import com.Blog.App.Exceptions.ResourceNotFoundException;
import com.Blog.App.Payloads.CommentDto;
import com.Blog.App.repositories.CommentRepository;
import com.Blog.App.repositories.PostRepository;
import com.Blog.App.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto comment ,Integer postId) {
		PostEntity post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "Post", postId));
		
		CommentEntity com=this.modelMapper.map(comment, CommentEntity.class);
		
			com.setPostsMadeByUser(post);
		CommentEntity comEntity=this.commentRepository.save(com);
		
		return this.modelMapper.map(comEntity, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer CommentId) {
		
		//CommentEntity com=this.commentRepository.findById(CommentId).orElseThrow(()->new ResourceNotFoundException("CommentEntity","CommentId",CommentId));
		//this.commentRepository.delete(com);
		
		
		
		this.commentRepository.deleteById(CommentId);
		
	}

}
