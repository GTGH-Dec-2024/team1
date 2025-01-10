package com.team1.eventproject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;

@Service
public class ApprovalRequestServices {

	@Autowired
	EmployeeServices employeeServices;
	@Autowired
	EventServices eventServices;


	private ArrayList<ApprovalRequest> allRequests;

	public ApprovalRequestServices() {
		allRequests = new ArrayList<>();
	}

	public void makeApprovalRequest(Organizer anOrganizer, String type, LocalDateTime createdAt, int organizerID,
			Event anEvent, String comments) {

		//

	}

	public void acceptRequest(int requestID, int employeeID) {
		
		ApprovalRequest tempRequest = this.isRequestValid(requestID, employeeID); 
		if (tempRequest == null) 
			return;

		if (tempRequest.getType() == "register")
		{
			tempRequest.getAnEvent().setStatus("approved");
		}
		else if (tempRequest.getType()=="delete")
			
		{
			//tempRequest.getAnEvent().deleteEvent(this);
		}
		
		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);
		
		closeRequest(tempRequest, tempEmployee, "The request has been accepted");

		System.out.println("The employee " + tempEmployee.getName() + " "+ tempEmployee.getSurname()+ 
				" has just ACCEPTED to " + tempRequest.getType()+ "the following event: " + tempRequest.getAnEvent()+"\n");

	}
	
	
	
public void denyRequest(int requestID, int employeeID) {
	
	ApprovalRequest tempRequest = this.isRequestValid(requestID, employeeID); 
	if (tempRequest == null) 
		return;
	
	if (tempRequest.getType() == "register")
	{
		tempRequest.getAnEvent().setStatus("not-approved");
	}
	/*
	 * If the request is to delete an Event, when the employee
	 * denies it the Event status remains the same. Therefore,
	 * we don't need an "else" statement here".
	 * 
	 * The only thing that changes is that the request is closed
	 * 
	 */
	Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);
	
	closeRequest(tempRequest, tempEmployee, "The request has been denied");

	System.out.println("The employee " + tempEmployee.getName() + " "+ tempEmployee.getSurname()+ 
			" has just DENIED to " + tempRequest.getType()+ "the following event: " + tempRequest.getAnEvent()+"\n");
	}




	private ApprovalRequest isRequestValid(int requestID, int employeeID)
	{
		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);

		if (tempEmployee == null) {
			System.out.println("There is no employee with this id! The request can't be handled");
			return null;
		}

		ApprovalRequest tempRequest = this.getApprovalRequestUsingID(requestID);
		
		if (tempRequest == null) {
			System.out.println("The request ID is invalid");
			return null;
		}

		if (tempRequest.getStatus().equalsIgnoreCase("closed")) {
			System.out.println("The request with ID: " + requestID + " has already been handled.");
			return null;
		}
		
		
		return tempRequest;
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
	 * Used by the employee to see which requests stil haven't been handled.
	 * 
	 * When a request hasn't been handled yet, its status is "open"
	 */
	public void getPendingRequests() {
		ArrayList<ApprovalRequest> pendingRequests = new ArrayList<>();
		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getStatus().equals("open")) {
				pendingRequests.add(aRequest);
			}
		}
		System.out.println("--The pending requests are--/n" + pendingRequests);
	}

	/*
	 * Returns all the ApprovalRequests that have been handled by an employee.
	 * 
	 * We give an Employee object and it returns a list of all the closed requests
	 * that were handled by him/her
	 * 
	 */
	public ArrayList<ApprovalRequest> getHandlingsBy(Employee anEmployee) {
		ArrayList<ApprovalRequest> myHandlings = new ArrayList<>();

		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getStatus().equals("closed") && aRequest.getHandledBy().equals(anEmployee)) {
				myHandlings.add(aRequest);
			}
		}

		return myHandlings;
	}

	public ApprovalRequest getApprovalRequestUsingID(int id) {
		for (ApprovalRequest temp : allRequests) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		return null;
	}

}
