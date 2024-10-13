package com.Blog.App.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	
	private Integer ID;
	
//	@Size(min = 4,message = "Min size of category title is 4")
	private String categoryTitle;
//	@Size(min = 10, message = "min size of cateogry desc is 10")
	private String categoryDescription;

	
}
