
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
		 * It follows different steps depending on the type of the request
		 * ("add" or "delete")
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
			
			else
			{
				//if it is a request to add an event
					if (aRequest.getType().equals("add"))
						{
							aRequest.getAnEvent().setStatus("approved");
							aRequest.handleRequest(this, "The event is now approved");
							
							System.out.println("You have just approved the following event: " 
							+ aRequest.getAnEvent().getTitle());
							
							
						}
				
				//if it is a request to delete an event
					else if (aRequest.getType().equals("delete"))
						{
							this.deleteEvent(aRequest.getAnEvent());
						}
					
				
			}
	}
	
	
	
	
	public void rejectRequest(ApprovalRequest aRequest) 
	{
		 if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event");
			}
		
		 else if (aRequest.getStatus().equals("closed"))
			{
				System.out.println("The request has already been handled");
			}
		
		 else 
			{
				//if it is a request to add the event, the employee makes its 
			 	//status not-approved
					if (aRequest.getType().equals("add"))
						{
							aRequest.handleRequest(this, "The event is NOT approved");
							aRequest.getAnEvent().setStatus("not-approved");
						}
				
				//if it is a request for deletion, it closes the request but no
				//changes are made to the status of the event
					else if (aRequest.getType().equals("delete"))
						{
							aRequest.handleRequest(this, "The deletion of the event is NOT approved");
						}
					
					
			System.out.println("You have NOT approved to " + aRequest.getType()
						+"the following event: " + aRequest.getAnEvent().getTitle());
			}
	}
	 
	 
	
	public void deleteEvent(Event anEvent)
	/*
	 * An Employee can delete any event with no request needed, just by calling
	 * this method. 
	 * 
	 * If an approvalRequest is made by the Organizer, this
	 * method is called by the acceptRequest method.
	 * 
	 */
	{
		anEvent.setStatus("deleted");
		System.out.println("You have deleted/cancelled the following event: "+ anEvent.getTitle());
	}
	

	
}