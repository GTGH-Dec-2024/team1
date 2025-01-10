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
	
	

	/*
	 * EMPLOYEE SERVICES!
	 * An Employee can delete any event with no request needed, just by calling
	 * this method. 
	 * 
	 * If an approvalRequest has been made by the Organizer, this
	 * method is called by the acceptRequest method.
	 * 
	 */
	public void deleteEvent(Event anEvent)	
	{
		anEvent.setStatus("deleted");
		System.out.println("You have deleted the following event: "+ anEvent.getTitle()+"\n");
	}
	
	
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}



}
