
public class ApprovalRequest {
	private String type; //isws ennoei an einai gia add h delete
	private String status; // einai pending, approved h rejected
	private String createdAt; //tsekare paketo me meres/wres
	private String closedAt; //tsekare paketo me meres/wres
	private String handledBy; //den exw idea, isws null h onoma ypallilou
	private String comments;
	
	private Organizer submittedBy;
	private Event anEvent;
	
	
	public ApprovalRequest(String type, String status, String createdAt, String closedAt, String handledBy,
			String comments, Organizer submittedBy, Event anEvent) {
		this.type = type;
		this.status = "pending"; 
		/*o organizer kanei to request opote by default pending
		 * pending/approved/not-approved
		 */
		this.createdAt = createdAt;
		this.closedAt = null;
		this.handledBy = null;
		//otan ginetai submit to request den ta kserw auta
		this.comments = comments;
		this.submittedBy = submittedBy;
		this.anEvent = anEvent;
	}
	
	
	public void handleRequest(Event anEvent)
	{
		/*
		 * thn xrisimopoiei o Employee gia na pei oti to request
		 * einai approved h oxi
		 * 
		 * Enimerwnw ta katallila pedia (apo poion egine handled,
		 * pote ekleise klp)
		 * 
		 * enimerwnw kai to pedio status tou event me setter
		 * 
		 */
	}
	


}
