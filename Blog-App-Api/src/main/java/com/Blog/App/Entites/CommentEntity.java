package com.Blog.App.Entites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Id;

@Entity
@Table(name="Comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String comment;
	
	
	@ManyToOne
	private PostEntity postsMadeByUser;
	
	

}
