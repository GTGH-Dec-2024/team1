package com.team1.eventproject.entities;

public abstract class User {
	protected String name;
	protected String surname;
	
	
	public User(String name, String surname) {
		this.name = name;
		this.surname = surname;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	


}
