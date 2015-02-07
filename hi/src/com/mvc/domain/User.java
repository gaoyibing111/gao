package com.mvc.domain;



public class User extends PageBean{
	
	
	private int id;
	private String name;
	private Integer age;
	private String email;
	private String telephone;
	private String address;

	private String userAge1;
	private String userAge2;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getUserAge1() {
		return userAge1;
	}
	public void setUserAge1(String userAge1) {
		this.userAge1 = userAge1;
	}
	public String getUserAge2() {
		return userAge2;
	}
	public void setUserAge2(String userAge2) {
		this.userAge2 = userAge2;
	}
	



	
	
	
	
}
