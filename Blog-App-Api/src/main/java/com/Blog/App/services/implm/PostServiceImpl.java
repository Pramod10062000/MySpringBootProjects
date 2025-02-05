package com.Blog.App.services.implm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.Blog.App.Entites.Catogories;
import com.Blog.App.Entites.PostEntity;
import com.Blog.App.Entites.UserEntity;
import com.Blog.App.Exceptions.ResourceNotFoundException;
import com.Blog.App.Payloads.PostDto;
import com.Blog.App.Payloads.PostResponse;
import com.Blog.App.repositories.CategoryRepository;
import com.Blog.App.repositories.PostRepository;
import com.Blog.App.repositories.UserRepository;
import com.Blog.App.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository ;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public PostDto create(PostDto postDto,Integer userId ,Integer catId) {
		System.out.println(postDto);
		
	UserEntity u = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
	System.out.println(u);
		
	Catogories cat=this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", catId));
	System.out.println(cat);
		PostEntity postEntity=this.modelMapper.map(postDto, PostEntity.class);

		postEntity.setImageName("default.png");
		postEntity.setAddedDate(new Date());
		postEntity.setUser(u);
		System.out.println(u);
		postEntity.setCategory(cat);
		System.out.println(cat);
     	PostEntity postCreated=this.postRepository.save(postEntity);
	
	
		return this.modelMapper.map(postCreated, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto post, int postId) {
		PostEntity postEntity=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		
		postEntity.setContent(post.getContent());
		postEntity.setPostTitle(post.getPostTitle());
		postEntity.setImageName(post.getImageName());
		
		PostEntity updated=this.postRepository.save(postEntity);
		return this.modelMapper.map(updated, PostDto.class);
	}

	@Override
	public void deletePost( Integer postId) {
		PostEntity post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","PostId", postId));
		this.postRepository.delete(post);
	}

	@Override
	public PostDto getPostById(int PostId) {
		PostEntity entity=this.postRepository.findById(PostId).orElseThrow(()->new ResourceNotFoundException("Post", "PostId", PostId));
		return this.modelMapper.map(entity, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer PageSize,String sortBy,String sortDir) {
		
		//Pagination Logic with Sorting
		
		//sortBy contains name of the field on the bases on which u are sortinG
		//Sort.by() is used to create a object of sort 
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort=Sort.by(sortBy).descending();
		}
		Pageable page=PageRequest.of(pageNumber,PageSize,sort);
		Page<PostEntity>p=this.postRepository.findAll(page);
		List<PostEntity>post=p.getContent();
		 List<PostDto> postDto=post.stream().map((e)->this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
		 PostResponse postResponse=new PostResponse();
			postResponse.setContent(postDto);
			postResponse.setPageNumber(p.getNumber());
			postResponse.setPageSize(p.getSize());
			postResponse.setTotalElements(p.getTotalElements());
			postResponse.setTotalPages(p.getTotalPages());
			postResponse.setLastPage(p.isLast());
			System.out.println(postResponse);
		return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer catId) {
		Catogories cat=this.categoryRepository.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", catId));
		List<PostEntity> post=this.postRepository.findBycategory(cat);
		List<PostDto> ptd=post.stream().map((p)->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		return ptd;
//	return null;
	}

	@Override
	public List<PostDto> getPostMadeByUser(Integer userId) {
		UserEntity user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "UserId", userId));
		List<PostEntity> postEntity=this.postRepository.findByUser(user);
	    List<PostDto>postDto=postEntity.stream().map((e)->this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
		return postDto;
//		return null;
	}

	@Override
	public List<PostDto> searchByKeyword(String keyword) {
		
		List<PostEntity> ptdto=this.postRepository.findBypostTitleContaining(keyword);
		List<PostDto>dto=ptdto.stream().map((e)->this.modelMapper.map(e, PostDto.class)).collect(Collectors.toList());
		return dto;
	}

}
