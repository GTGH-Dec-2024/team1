package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User {

	private String email;
	private static int counter = 1;
	private int id;
	private ArrayList <Event> deletionsHistory;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.id = counter++; //the id is given automatically by the program
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
	
	



}
