
import java.time.LocalDateTime;

public class ApprovalRequest {
	private String type; //is it to register or delete event
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
	
	
/*
 * Changes the status of the request and the event.
 * Updates the "closed-at" and "handled by" fields
 */

	
	/*
	 * Na pairnei ws orisma gia ti pragma einai to request. An
	 * einai request gia add h gia delete
	 * 
	 * 
	 * an einai gia delete kai exw kalesei tin approved,
	 * tote kaleitai i delete tis employee kai svinetai aytomata to event
	 * 
	 */
	public void handleRequest(boolean isApproved, Employee employee, String comment)
	{
		if (isApproved)
		{
			anEvent.setStatus("approved");
		}
			
		else
		{
			anEvent.setStatus("not-approved");
		}
		
		this.status = "closed";
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


	public String getType() {
		return type;
	}
	
	
	
}
