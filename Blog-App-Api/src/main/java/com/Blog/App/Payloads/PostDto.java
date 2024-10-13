package com.Blog.App.Payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Blog.App.Entites.Catogories;
import com.Blog.App.Entites.CommentEntity;
import com.Blog.App.Entites.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDto {

	private Integer postId;
	
	private String postTitle; 
	
	private String content;
	
	private String imageName; 
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	private List<CommentDto> comments =new ArrayList<CommentDto>() ;
}


//{
//"postId": 0,
//"postTitle": null,
//"content": null,
//"imageName": null,
//"addedDate": null,
//"category": null,
//"user": null
//}
//data goes according to dto