package com.Blog.App.Payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostResponse {
	
	List<PostDto>content;
	private long pageNumber;
	private long pageSize;
	private long TotalElements;
	private long totalPages;
	private boolean lastPage;

}
