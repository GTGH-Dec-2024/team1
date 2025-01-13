package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User {

	private String email;
	private Integer id;
	private String status; 
	// active or deleted. When an entity gets "deleted", it doesn't
	//actually get removed from the list. Its status just changes 
	//to "deleted"
	private ArrayList <Event> deletionsHistory;

	public Employee(String name, String surname, String email, Integer id) {
		super(name, surname);
		this.id = id;
		this.email = email;
		this.status = "active";
		deletionsHistory = new ArrayList<>();
	}
	
	
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}


	public Integer getId() {
		return id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
   // Na valoume mia toString?

}
