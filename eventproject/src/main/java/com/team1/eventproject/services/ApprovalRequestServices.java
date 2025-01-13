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

	/*
	 * Makes an ApprovalRequest object by using the ApprovalRequest constructor. By
	 * default, when making a request its status is "open"
	 * 
	 * After making the ApprovalRequest, it is added to the list of all the
	 * Requests.
	 * 
	 * 
	 */
	public void addApprovalRequest(String type, Integer OrganizerID, Integer eventID, String comments) {
		
		Organizer tempOrganizer = organizerServices.getOrganizerUsingID(OrganizerID);
		Event tempEvent = eventServices.getEventUsingID(eventID);

		/*
		 * We want all IDs to be given automatically. Therefore, we use the allRequests
		 * list to help us. If the list is empty, then we know it is the first object
		 * that will be made so its id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added, and by
		 * increasing it by 1 we get the new id!
		 * 
		 */
		Integer id;
		if (allRequests.isEmpty()) {
			id = 1;

		} else {
			id = allRequests.get(allRequests.size() - 1).getId() + 1;
		}

		ApprovalRequest aRequest = new ApprovalRequest(type, LocalDateTime.now(), tempOrganizer, tempEvent, comments,
				id);

		allRequests.add(aRequest);

	}

	/*
	 * First, we check if the request is valid.
	 * 
	 * If it is a request to register an Event, we set its status to "approved".
	 * 
	 * If it is a request to delete an Event, we call the deleteEvent method from
	 * EventManager
	 * 
	 * Afterwards, the request is updated to "closed", and we add the employee who
	 * handled it. A message is printed.
	 * 
	 */

	public String acceptRequest(Integer requestID, Integer employeeID) {

		ValidService validationResult = this.isValid(requestID, employeeID);

		if (!validationResult.isValid()) {
			return validationResult.getMessage();
		}

		ApprovalRequest tempRequest = validationResult.getRequest();

		if (tempRequest.getType().equalsIgnoreCase("register")) {
			tempRequest.getAnEvent().setStatus("approved");

		}

		else {
			eventServices.deleteEvent(tempRequest.getAnEvent().getId());
		}

		tempRequest.setIsApproved(true); // used to return a list of approved requests
		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);
		this.closeRequest(tempRequest, tempEmployee, "The request has been accepted");

		return "The employee " + tempEmployee.getName() + " " + tempEmployee.getSurname() + " has just ACCEPTED to "
				+ tempRequest.getType() + "the following event: " + tempRequest.getAnEvent() + "\n";

	}

	/*
	 * First, we check if the request is valid.
	 * 
	 * If it is a request to register an Event, we set its status to "not-approved".
	 * 
	 * If the request is to delete an Event, when the employee denies it the Event
	 * status remains the same
	 * 
	 * Afterwards, the request is updated to "closed", and we add the employee who
	 * handled it. A message is printed.
	 * 
	 */
	public String denyRequest(Integer requestID, Integer employeeID) {

		ValidService validationResult = this.isValid(requestID, employeeID);

		if (!validationResult.isValid()) {
			return validationResult.getMessage();
		}

		ApprovalRequest tempRequest = validationResult.getRequest();
		if (tempRequest.getType().equalsIgnoreCase("register")) {
			tempRequest.getAnEvent().setStatus("not-approved");
		}
		// no change when the request type is "delete"

		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);

		closeRequest(tempRequest, tempEmployee, "The request has been denied");

		return "The employee " + tempEmployee.getName() + " " + tempEmployee.getSurname() + " has just DENIED to "
						+ tempRequest.getType() + "the following event: " + tempRequest.getAnEvent() + "\n";
	}

	/*
	 * Checks if the request can be processed.
	 * 
	 * * Both the ApprovalRequest and the Employee objects must be non-null, and the Request
	 * status must not be "closed". If any of these conditions fail, the method
	 * returns a ValidService object with the appropriate error message.
	 * 
	 * This method was made to avoid duplicate code in acceptRequest and
	 * deleteReques. The ValidService class was made to help.
	 * 
	 */
	private ValidService isValid(Integer requestID, Integer employeeID) {
		Employee tempEmployee = employeeServices.getEmployeeUsingID(employeeID);

		if (tempEmployee == null) {
			return new ValidService(null, false, "There is no employee with this id! The request can't be handled");
		}

		ApprovalRequest tempRequest = this.getApprovalRequestUsingID(requestID);

		if (tempRequest == null) {
			return new ValidService(null, false, "The request ID is invalid");
		}

		if (tempRequest.getStatus().equalsIgnoreCase("closed")) {
			return new ValidService(tempRequest, false,
					"The request with ID: " + requestID + " has already been handled.");
		}

		return new ValidService(tempRequest, true, "Request is valid");
	}

	/*
	 * Updates the necessary fields of an ApprovalRequest object to close the
	 * request
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
	public String getPendingRequests() {
		ArrayList<ApprovalRequest> pendingRequests = new ArrayList<>();
		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getStatus().equals("open")) {
				pendingRequests.add(aRequest);
			}
		}
		return "--The pending requests are--/n" + pendingRequests;
	}

	
	
	/*
	 * Returns all the ApprovalRequests that have been approved by an employee
	 */
	public ArrayList<ApprovalRequest> getApprovedRequests() {
		ArrayList<ApprovalRequest> approvedRequests = new ArrayList<>();

		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getIsApproved()) {
				approvedRequests.add(aRequest);
			}
		}
		return approvedRequests;

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
	 * Given the ID of an ApprovalRequest object, it returns the object
	 * 
	 */
	public ApprovalRequest getApprovalRequestUsingID(Integer id) {
		for (ApprovalRequest temp : allRequests) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		return null;
	}

	
	public String removeApprovalRequest(Integer id) {
		ApprovalRequest temp = getApprovalRequestUsingID(id);
		
		if (temp.getStatus().equalsIgnoreCase("open"))
		{
			allRequests.remove(temp);
			return "The request to " + temp.getType() +" the event: "
					+temp.getAnEvent().getTitle()+ " has been removed.";
		}
		else
			return "The request has already been handled, it can't be removed";
		
	}
}
