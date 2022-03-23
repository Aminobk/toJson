package com.example.demo.Util;

public class user {
	private String FirstName;
	private String Lastname;
	private Integer Age;
	
	public user(String FirstName,String LastName,Integer Age) {
		this.FirstName=FirstName;
		this.Lastname=LastName;
		this.Age=Age;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	

}