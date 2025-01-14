package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User {

	private String email;
	private Integer id;
	private String status; 
	// active or deleted. When an entity gets "deleted", it doesn't
	//actually get removed from the list. Its status just changes 
	//to "deleted"
	//private ArrayList <Event> deletionsHistory;

	public Employee(String name, String surname, String email, Integer id) {
		super(name, surname);
		this.id = id;
		this.email = email;
		this.status = "active";
		//deletionsHistory = new ArrayList<>();
	}
	
	/*
	 * a method for the "nice to have" feature of keeping
	 * an archive of all the Events an employee has deleted.
	 * Probably will not be implemented
	 * 
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}
*/

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

	@Override
	public String toString() {
		return "Employee [email=" + email + ", id=" + id + ", status=" + status + "]";
	}
	
	
		
}
