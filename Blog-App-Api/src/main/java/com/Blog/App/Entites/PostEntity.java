package com.Blog.App.Entites;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Post_Table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Post_Id")
	private int postId;
	
	
	@Column(name="Post_Title", length = 1000)
//	@NotNull(message = "Post Title cannot be empty")
	private String postTitle;
	
	
	@Column(name="Post_Content",length = 1000)
//	@NotNull(message = "Post Content cannot be empty")
	private String content;
	
	@Column(name = "Image_Name")
//	@NotNull(message = "Image Name cannot be empty")
	private String imageName;
	
//	@NotNull(message = "Date cannot be empty")
	@Column(name = "Date")
	private Date addedDate;
	
	
	//Single Post belongs to single User
	//multiple post can also belong to single user
	// a single post can belong to single category
	//multiple posts can belong to single category as well
	
	@ManyToOne
	private Catogories category;
	@ManyToOne
	private UserEntity user;
	
	
	@OneToMany(mappedBy ="postsMadeByUser", cascade = CascadeType.ALL)
	private List<CommentEntity> comments =new ArrayList<CommentEntity>() ;
	
	
	
}
