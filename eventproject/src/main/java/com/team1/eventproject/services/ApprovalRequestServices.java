package com.team1.eventproject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;

@Service
public class ApprovalRequestServices {


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
	public String addApprovalRequest(String type, Integer organizerID, Integer eventID, String comments) {
		
		if (!type.equalsIgnoreCase("delete") && !type.equals("add") )
		{
			return "The only acceptable types of requests are add/delete.";
		}
		
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

		ApprovalRequest aRequest = new ApprovalRequest(type, LocalDateTime.now(), organizerID, eventID, comments,id);

		allRequests.add(aRequest);
		
		return "A request to " +type+ " the event with ID" +eventID +" has been added."
				+ "The id of the request is: " +aRequest.getId();

	}


	/*
	 * Updates the necessary fields of an ApprovalRequest object to close the
	 * request
	 * 
	 */

	public void closeRequest(Integer requestID, Integer employeeID, String comment) {
		ApprovalRequest temp = this.getApprovalRequestUsingID(requestID);
		temp.setStatus("closed");
		temp.setClosedAt(LocalDateTime.now());
		temp.setHandledBy(employeeID);

		if (!comment.isBlank()) {
			temp.addComments("\nEmployee's comment: " + comment);
		}
	}

	
	/*
	 * Returns the ApprovalRequests that have been submitted for a 
	 * specific event
	 */
	public List<ApprovalRequest> getRequestsForEvent(Integer eventID) {
		ArrayList<ApprovalRequest> requests = new ArrayList<>();
		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getEventID().equals(eventID)) {
				requests.add(aRequest);
			}
		}
		return requests;
	}

	/*
	 * Used by the employee to see which requests still haven't been handled.
	 * 
	 * When a request hasn't been handled yet, its status is "open"
	 */
	public List<ApprovalRequest> getPendingRequests() {
		ArrayList<ApprovalRequest> pendingRequests = new ArrayList<>();
		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getStatus().equals("open")) {
				pendingRequests.add(aRequest);
			}
		}
		return pendingRequests;
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
	 * We give an Employee ID and it returns a list of all the closed requests
	 * that were handled by him/her
	 * 
	 */
	public ArrayList<ApprovalRequest> getHandlingsBy(Integer employeeID) {
		ArrayList<ApprovalRequest> myHandlings = new ArrayList<>();

		for (ApprovalRequest aRequest : allRequests) {
			if (aRequest.getStatus().equals("closed") && aRequest.getHandledBy().equals(employeeID)) {
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
		
		if (temp == null)
		{
			return "There is no request with the id you provided!";
		}
		
		if (temp.getStatus().equalsIgnoreCase("open"))
		{
			allRequests.remove(temp);
			return "The request to " + temp.getType() +" the event with ID "
					+temp.getEventID()+ " has been removed.";
		}
		else
			return "The request has already been handled, it can't be removed";
		
	}
	
	
	public List<ApprovalRequest> getAllRequests()
	{
		return allRequests;
	}
	
	
	public ArrayList<ApprovalRequest> getDeniedRequests() {
		ArrayList<ApprovalRequest> deniedRequests = new ArrayList<>();

		for (ApprovalRequest aRequest : allRequests) {
			if (!aRequest.getIsApproved()) {
				deniedRequests.add(aRequest);
			}
		}
		return deniedRequests;

	}
}
