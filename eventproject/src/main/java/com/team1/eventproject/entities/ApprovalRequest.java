package com.team1.eventproject.entities;

import java.time.LocalDateTime;

public class ApprovalRequest {
	private Integer id;  
	private String type; //is it to "register" or "delete" event
	private String status; 
	//is it open (waiting for answer from employee) or closed (employee has answered)
	private LocalDateTime createdAt;
	private LocalDateTime closedAt; 
	private Employee handledBy; 
	private String comments;
	private Boolean isApproved;
	
	private Integer submittedBy;//the ID of the Organizer
	private Integer EventID;//the ID of the Event
	
	
	public ApprovalRequest(String type, LocalDateTime createdAt,
			Integer submittedBy, Integer EventID, String comments, Integer id) {
		this.id = id;//the id is given automatically by the program
		this.type = type;
		this.createdAt = createdAt;
		this.submittedBy = submittedBy;
		this.EventID = EventID;
		this.comments = "Organizer's comment: " + comments;
		this.status = "open"; //by default, since the organizer makes the request
		this.closedAt = null; //based on when the employee handles it
		this.handledBy = null; //based on which employee handles it
		this.isApproved = false; //will be set to true if the request is approved
	}
	
	
	

	public String getStatus() 
	{
		return status;
	}


	public Integer getEventID() {
		return EventID;
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




	public Employee getHandledBy() {
		return handledBy;
	}
	
	

	public Integer getId() {
		return id;
	}



	@Override
	public String toString() {	
		String print = "REQUEST ID: " +id+ "\n A request to " +type+ " the event with ID"+EventID+" was created at" + createdAt;
		if (closedAt!=null && handledBy!=null )
			print += "\n It was handled at " +closedAt;
		
		return print;
	}




	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}




	public Boolean getIsApproved() {
		return isApproved;
	}
	
	
	
	
	
}
