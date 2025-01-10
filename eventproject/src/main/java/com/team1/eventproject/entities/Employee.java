package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User {

	private String email;
	private ArrayList <Event> deletionsHistory;

	public Employee(int id,String name, String surname, String email) {
		super(name, surname, id);
		this.email = email;
		deletionsHistory = new ArrayList<>();
	}
	
	
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}



}
