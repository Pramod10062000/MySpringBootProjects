package com.Blog.App.Entites;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Catogories {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	
	@Column(name="categoryTitle",length = 100,nullable = false)
	@NotBlank
	@Size(min = 4 ,message = "Min size is 4")
	private String categoryTitle;
	
	
	@NotBlank
	@Size(min = 10 , message = "min size is 10")
	@Column(name="categoryDescription")
	private String categoryDescription;
	
	
	
//A single category can have multiple posts in it like a cricket category can have 20 post related to it 
	
@OneToMany(mappedBy ="category",cascade = CascadeType.ALL ,fetch = FetchType.LAZY )
private List<PostEntity> posts =new ArrayList<>();
	
	
	
	
	
	
	
	
	
	
	
	
}
