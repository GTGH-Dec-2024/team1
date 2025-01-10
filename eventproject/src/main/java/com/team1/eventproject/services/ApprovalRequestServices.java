package com.team1.eventproject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;

public class ApprovalRequestServices {
	
	@Autowired EmployeeServices employeeServices;
	
	
	private ArrayList<ApprovalRequest> allRequests;

	public ApprovalRequestServices() {
		allRequests = new ArrayList<>();
	}


	public void makeApprovalRequest (Organizer anOrganizer, String type, LocalDateTime createdAt,
			int organizerID, Event anEvent, String comments)
	{
		
	//		
		
		
	}
	
	/*
	 * An employee handles a request for the registration of an event.
	 * 
	 * This method takes an Event, an Employee, and a boolean "isApproved"
	 * attribute. The isApproved is true if the Employee wants to approve the Event
	 * and false if he/she doesn't want to approve it
	 * 
	 * Depending on isApproved, the status of the Event changes, from pending to
	 * "approved" or "not-approved". The event status is updated when the request is
	 * closed by calling the closeRequest
	 * 
	 */
	public void handleRegistrationRequest(Event anEvent, int employeeID, boolean isApproved) {
		ApprovalRequest request = this.getApprovalRequest(anEvent, "register");

		if (request == null) {
			System.out.println("The organizer of the event has not made" + "a registration request for it!");
		}

		else if (request.getStatus().equalsIgnoreCase("closed")) {
			System.out.println("The registration request for this event" + "has already been handled.");
		}

		else {
			if (isApproved) {
				request.getAnEvent().setStatus("approved");
				closeRequest(request, anEmployee, "The event " + anEvent.getTitle() + " is now approved");

				System.out.println("The employee " + anEmployee.getName() + " has just approved to register "
						+ "the following event: " + request.getAnEvent().getTitle() + "\n");
			} else {
				request.getAnEvent().setStatus("not-approved");
				closeRequest(request, anEmployee,
						"The event " + anEvent.getTitle() + " is NOT approved by the employee");

				System.out.println("The employee " + anEmployee.getName() + " has NOT approved to register "
						+ "the following event: " + request.getAnEvent().getTitle() + "\n");
			}

		}

	}

	
	public void handleDeletionRequest(Event anEvent, int employeeID, boolean isApproved) {
		
		ApprovalRequest request = this.getApprovalRequest(anEvent, "delete");
		
		if (request == null) {
			System.out.println("The organizer of the event has not made" + 
		"a deletion request for it!\n");
		}

		else if (request.getStatus().equalsIgnoreCase("closed")) 
		{
			System.out.println("The deletion request for this event has already been handled.\n");
		}
		else
			if (isApproved) {
			
			//AnEvent.deleteEvent(this);
			closeRequest(request, anEmployee, "The event " + anEvent.getTitle() + 
					" has been deleted");

//			System.out.println("The employee " + anEmployee.getName() + " has deleted "
//					+ "the following event: " + request.getAnEvent().getTitle() + "\n");
			}else {
				closeRequest(request, anEmployee, "The event " + anEvent.getTitle() + 
						" has NOT been deleted");
				
				System.out.println("The employee " + anEmployee.getName() + " has decided "
					+ " NOT to delete the following event: " + anEvent.getTitle());
			}

	}
	
	
	private void closeRequest(ApprovalRequest aRequest, Employee anEmployee, String comment) {
		aRequest.setStatus("closed");
		aRequest.setClosedAt(LocalDateTime.now());
		aRequest.setHandledBy(anEmployee);

		if (!comment.isBlank()) {
			aRequest.addComments("\nEmployee's comment: " + comment);
		}
	}



	public ApprovalRequest getApprovalRequest(Event anEvent, String requestType) {
		for (ApprovalRequest i : allRequests) {
			if (i.getAnEvent().equals(anEvent) && i.getType().equalsIgnoreCase(requestType)) {

				return i;
			}
		}
		return null;
	}
	
	
	/*
	 * Used by the employee to see which requests stil haven't been
	 * handled. 
	 * 
	 * When a request hasn't been handled yet, its status is "open"
	 */
	public void getPendingRequests()
	{
		ArrayList <ApprovalRequest> pendingRequests = new ArrayList<>();
		for (ApprovalRequest aRequest: allRequests)
		{
			if(aRequest.getStatus().equals("open"))
			{
				pendingRequests.add(aRequest);
			}
		}
		System.out.println("--The pending requests are--/n" + pendingRequests);
	}
	
		
	/*
	 * Returns all the ApprovalRequests that have been handled by
	 * an employee.
	 * 
	 * We give an Employee object and it returns a list
	 * of all the closed requests that were handeld by
	 * him/her
	 * 
	 */
	public ArrayList<ApprovalRequest> getHandlingsBy (Employee anEmployee)
	{
		ArrayList<ApprovalRequest> myHandlings = new ArrayList<>();
		
		for (ApprovalRequest aRequest: allRequests)
		{
			if(aRequest.getStatus().equals("closed")
					&& aRequest.getHandledBy().equals(anEmployee))
			{
				myHandlings.add(aRequest);
			}
		}
		
		return myHandlings;
	}
}
