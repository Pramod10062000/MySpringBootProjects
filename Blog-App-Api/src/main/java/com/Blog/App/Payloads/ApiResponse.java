package com.Blog.App.Payloads;

public class ApiResponse {

	String messString;
	
	boolean sucess;
	public String getMessString() {
		return messString;
	}
	public void setMessString(String messString) {
		this.messString = messString;
	}
	
	public boolean isSucess() {
		return sucess;
	}
	public void setSucess(boolean sucess) {
		this.sucess = sucess;
	}
	public ApiResponse(String messString,  boolean sucess) {
		this.messString = messString;
	
		this.sucess = sucess;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
