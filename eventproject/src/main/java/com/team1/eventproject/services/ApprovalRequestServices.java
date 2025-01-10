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
	@Autowired
	OrganizerServices organizerServices;

	private ArrayList<ApprovalRequest> allRequests;

	public ApprovalRequestServices() {
		allRequests = new ArrayList<>();
	}

	public void addApprovalRequest(String type, int OrganizerID, int eventID, String comments)
	{
		Organizer tempOrganizer = organizerServices.getOrganizerUsingID(OrganizerID);
		Event tempEvent = eventServices.getEventUsingID(eventID);
		
		ApprovalRequest aRequest = new ApprovalRequest(type, LocalDateTime.now(),
				tempOrganizer, tempEvent, comments);
		allRequests.add(aRequest);
	  
	}
	
	
	
	/*
	 * First, we check if the request is valid.
	 * 
	 * If it is a request to register an Event, we set its status
	 * to "approved".
	 * 
	 * If it is a request to delete an Event, we call the deleteEvent
	 * method from EventManager
	 * 
	 * Afterwards, the request is updated to "closed", and we add the 
	 * employee who handled it. A message is printed.
	 * 
	 */

	public void acceptRequest(int requestID, int employeeID) {

		ApprovalRequest tempRequest = this.isValid(requestID, employeeID);

		if (tempRequest == null)
			return;

		if (tempRequest.getType().equalsIgnoreCase("register")) 
		{
			tempRequest.getAnEvent().setStatus("approved");
		}
		 
//		else
//		{
//			// tempRequest.getAnEvent().deleteEvent(eventID, employeeID);
//		}

		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);
		this.closeRequest(tempRequest, tempEmployee, "The request has been accepted");

		System.out.println(
				"The employee " + tempEmployee.getName() + " " + tempEmployee.getSurname() + " has just ACCEPTED to "
						+ tempRequest.getType() + "the following event: " + tempRequest.getAnEvent() + "\n");

}

	
	/*
	 * First, we check if the request is valid.
	 * 
	 * If it is a request to register an Event, we set its status
	 * to "not-approved".
	 * 
	 * If the request is to delete an Event, when the employee 
	 * denies it the Event status remains the same
	 * 
	 * Afterwards, the request is updated to "closed", and we add the 
	 * employee who handled it. A message is printed.
	 * 
	 */
	public void denyRequest(int requestID, int employeeID) {

		ApprovalRequest tempRequest = this.isValid(requestID, employeeID);
		if (tempRequest == null)
			return;

		if (tempRequest.getType().equalsIgnoreCase("register")) 
		{
			tempRequest.getAnEvent().setStatus("not-approved");
		}
		// no change when the request type is "delete"
		
		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);

		closeRequest(tempRequest, tempEmployee, "The request has been denied");

		System.out.println(
				"The employee " + tempEmployee.getName() + " " + tempEmployee.getSurname() + " has just DENIED to "
						+ tempRequest.getType() + "the following event: " + tempRequest.getAnEvent() + "\n");
	}

	
	/*
	 * Checks if the request can be processed. 
	 * 
	 * Both the Request and the Employee objects have to 
	 * be !=null, and the Request status must not be "closed"
	 * 
	 *
	 */
	private ApprovalRequest isValid(int requestID, int employeeID) {
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
	 * Updates the necessary fields of an ApprovalRequest object
	 * to close the request
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
	 * Used by the employee to see which requests still haven't been handled.
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

	
	/*
	 * Given the ID of an ApprovalRequest object, it returns the
	 * object
	 * 
	 */
	public ApprovalRequest getApprovalRequestUsingID(int id) {
		for (ApprovalRequest temp : allRequests) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		return null;
	}

}
