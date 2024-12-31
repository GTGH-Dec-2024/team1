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
	
	
	public void approveRequest(ApprovalRequest aRequest)
	{
		
		/*
		 * 
		 * We use the "status" field on the ApprovalRequest class so that we know
		 * if an Event is awaiting approval (status equals "open").
		 * 
		 * The employee approves the request, making the event "approved"
		 * 
		 * 
		 */
			if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event");
			}
			else if (aRequest.getStatus().equals("closed"))
			{
				System.out.println("The request has already been handled");
			}
			
			//if it is a request to add an event:
			else if(aRequest.getStatus().equals("open"))
			{
				
					if (aRequest.getType().equals("add"))
						{
							aRequest.handleRequest(true, this, "The event is now approved");
						}
			
					else if (aRequest.getType().equals("delete"))
						{
							//handle request gia deletion
						}
					
					
				System.out.println("You have just approved to " + aRequest.getType()
						+"the following event: " + aRequest.getAnEvent().getTitle());
			}
	}
	
	
	
	public void rejectRequest(ApprovalRequest aRequest) 
	 {
		 if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event");
			}
			else if(aRequest.getStatus().equals("open"))
			{
				aRequest.handleRequest(false, this, "The event is now not-approved");
				System.out.println("You have NOT given permission to " +aRequest.getType()+
						" the event.");
			}
			else
			{
				System.out.println("The request has already been handled");
			}
	 }
	 
	 
	
	public void deleteEvent(Event anEvent)
	/*
	 * An Employee can delete any event with no request needed
	 * if an Organizer wants to delete an Event, an ApprovalRequest
	* is made, and afterwards this method is called by handleRequest
	 */
	{
		anEvent.setStatus("deleted");
		System.out.println("You have deleted the following Event: "+ anEvent.getTitle());
	}
	

	
}