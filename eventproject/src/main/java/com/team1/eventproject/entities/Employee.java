package com.team1.eventproject.entities;

public class Employee extends User{
	private String email;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
	}
	
	
	
	/*
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
	


}
