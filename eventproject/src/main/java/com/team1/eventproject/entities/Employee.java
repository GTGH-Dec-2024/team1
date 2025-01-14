package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User {

	private String email;
	private int id;
	private ArrayList <Event> deletionsHistory;

	public Employee(String name, String surname, String email, int id) {
		super(name, surname);
		this.id = id;
		this.email = email;
		deletionsHistory = new ArrayList<>();
	}
	
	
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}


	public int getId() {
		return id;
	}



   // Na valoume mia toString?
	@Override
	public String toString() {
		return "Employee [email=" + email + ", id=" + id + ", deletionsHistory=" + deletionsHistory + "]";
	}
	
		
}
