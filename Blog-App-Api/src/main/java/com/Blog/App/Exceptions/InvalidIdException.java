package com.Blog.App.Exceptions;

public class InvalidIdException extends RuntimeException  {
	
	
	Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public InvalidIdException(Integer id) {
		super(String.format("%s: Id not found",id));
		this.id = id;
	}
	

}
