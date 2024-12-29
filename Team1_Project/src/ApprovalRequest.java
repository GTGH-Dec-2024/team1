
import java.time.LocalDateTime;

public class ApprovalRequest {
	private String type; //isws ennoei an einai gia add h delete
	private String status; //is it pending, approved or not-approved
	private LocalDateTime createdAt; //tsekare paketo me meres/wres
	private LocalDateTime closedAt; //dinei twrini imerominia kai wra
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
		this.status = "pending"; //by default, since the organizer makes the request
		this.closedAt = null; //based on when the employee handles it
		this.handledBy = null; //based on which employee handles it
	}
	
	
/*
 * Changes the status of the request and the event.
 * Updates the "closed-at" and "handled by" fields
 */
	
	public void handleRequest(boolean isApproved, Employee employee, String comment)
	{
		if (isApproved)
		{
			this.status = "approved";
			anEvent.setStatus("approved");
		}
			
		else
		{
			this.status = "not-approved";
			anEvent.setStatus("not-approved");
		}		
		
		closedAt = LocalDateTime.now();
		handledBy = employee;
		
		if (!comment.isBlank()) {
		    this.comments += "\nEmployee's comment: " + comment;
		}

		
	}


	public String getStatus() 
	{
		return status;
	}


	public Event getAnEvent() {
		return anEvent;
	}
	
	
	
}
