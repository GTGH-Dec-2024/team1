
/*
 * PROSEXW: O Employee mporei na diagrapsei monos tou Events!
 * Den mporei na ftiajei!
 * 
 * An o organizer ftiajei/diagrapsei Event omws, o Employee
 * prepei na dwsei to ok
 * 
 * 
 * NA ELEGXEI AN TO MAIL EINAI VALID??
 * 
 */

public class Employee extends User{
	private String email;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
	}
	
	
	public void approveEvent(ApprovalRequest aRequest)
	{
		
		/*
		 * 
		 * We use the "status" field on the ApprovalRequest class so that we know
		 * if an Event is awaiting approval. The employee utilizes that to 
		 * register the Event.
		 * 
		 */
			if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for"
						+ "the registration of this Event");
			}
			else if(aRequest.getStatus().equals("pending"))
			{
				aRequest.handleRequest(true, this, "The event is now approved");
				EventManager.getInstance().registerEvent(aRequest.getAnEvent());
				System.out.println("You have successfully approved and registered the event");
			}
			
	}
	
	public void deleteEvent(/*Event anEvent?? ApprovalRequest request??*/)
	{
		/*
		 * Edw den xreiazetai na uparxei request.
		 * 
		 * An uparxei to kaneis approved klp klp,
		 * alla eite uparxei eite oxi sigoura
		 * diagrafeis apo ti lista to Event tis EventManager
		 * 
		 * 
		 */
	}
	/*
	 * ISWS approveEvent kai deleteEvent na enwthoun????
	 * kai na ginoun px approveRequest??
	 * to vlepoyme
	 */
	
	 public void rejectRequest(ApprovalRequest request) 
	 {
		 /*
		  * An  kanw reject prepei na exw auth th methodo
		  * pou tha enimerwnei oti to request einai closed
		  * 
		  */
	 }
	

	
}
