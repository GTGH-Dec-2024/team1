package com.team1.eventproject.entities;

import java.util.ArrayList;

public class Employee extends User{
	private String email;
	private ArrayList <ApprovalRequest> pastHandlings;
	private ArrayList <Event> deletionsHistory;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
		pastHandlings = new ArrayList<>();
		deletionsHistory = new ArrayList<>();
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
	
	public void addpastHandlings(ApprovalRequest aRequest)
	{
		pastHandlings.add(aRequest);
	}


	public ArrayList<ApprovalRequest> getPastHandlings() {
		return pastHandlings;
	}
	
	public void addDeletion(Event anEvent)
	{
		deletionsHistory.add(anEvent);
	}



}
