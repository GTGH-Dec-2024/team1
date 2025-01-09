package com.team1.eventproject.entities;

import java.time.LocalDateTime;

public class ApprovalRequest {
	private String type; //is it to "register" or "delete" event
	private String status; 
	//is it open (waiting for answer from employee) or closed (employee has answered)
	private LocalDateTime createdAt;
	private LocalDateTime closedAt; 
	private Employee handledBy; 
	private String comments;
	
	private Organizer submittedBy;
	private Event anEvent;
	
	
	public ApprovalRequest(String type, LocalDateTime createdAt,
			Organizer submittedBy, Event anEvent, String comments) {
		this.type = type;
		this.createdAt = createdAt;
		this.submittedBy = submittedBy;
		this.anEvent = anEvent;
		this.comments = "Organizer's comment: " + comments;
		this.status = "open"; //by default, since the organizer makes the request
		this.closedAt = null; //based on when the employee handles it
		this.handledBy = null; //based on which employee handles it
		
	}
	
	
	

	public String getStatus() 
	{
		return status;
	}


	public Event getAnEvent() {
		return anEvent;
	}


	public String getType() {
		return type;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public void setHandledBy(Employee handledBy) {
		this.handledBy = handledBy;
	}




	public void addComments(String comments) {
		this.comments += comments;
	}




	public void setClosedAt(LocalDateTime closedAt) {
		this.closedAt = closedAt;
	}
	
	
	
}
