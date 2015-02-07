package com.mvc.domain;

public class Admin extends PageBean{
	@Override
	public String toString() {
		return "Admin [password=" + password + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	private Integer id;
	private String username;
	private String password;
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}

}
