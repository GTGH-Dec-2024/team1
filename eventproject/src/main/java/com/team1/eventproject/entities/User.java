package com.team1.eventproject.entities;

public abstract class User {
	protected String name;
	protected String surname;
	protected int id;
	
	
	public User(String name, String surname, int id) {
		this.name = name;
		this.surname = surname;
		this.id = id;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
	


}
