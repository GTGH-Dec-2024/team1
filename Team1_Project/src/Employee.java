
public class Employee extends User{
	private String email;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
	}
	
	
	
	/*
	 * We use the "status" field on the ApprovalRequest class so that we know
	 * if an event registration/deletion is awaiting approval (status equals "open").
	 * 
	 * The employee approves the request, making the status of the event "approved"
	 * 
	 * It follows different steps depending on the type of the request
	 * ("register" or "delete")
	 * 
	 */
	public void approveRequest(ApprovalRequest aRequest)
	{
		
		
			if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event"+"\n");
			}
			
			else if (aRequest.getStatus().equalsIgnoreCase("closed"))
			{
				System.out.println("The request has already been handled"+"\n");
			}
			
			else
			{
				//if it is a request to add an event,it just sets 
				//its status as "approved"
					if (aRequest.getType().equalsIgnoreCase("register"))
						{
							aRequest.getAnEvent().setStatus("approved");
							aRequest.handleRequest(this, "The event is now approved");
							
							System.out.println("You have just approved the following event: " 
							+ aRequest.getAnEvent().getTitle()+"\n");
							
							
						}
				
				//if it is a request to delete an event, it calls the employee's 
				//deleteEvent method
					else if (aRequest.getType().equalsIgnoreCase("delete"))
						{
							this.deleteEvent(aRequest.getAnEvent());
							aRequest.handleRequest(this, "The event is now deleted");
						}
					
				
			}
	}
	
	
	
	/*
	 *  We use the "status" field on the ApprovalRequest class so that we know
	 * if an event registration/deletion is awaiting approval (status equals "open").
	 * 
	 * The employee does NOT approve the request, making the status of the 
	 * event "not- approved"
	 * 
	 * It follows different steps depending on the type of the request
	 * ("add" or "delete")
	 * 
	 */
	public void rejectRequest(ApprovalRequest aRequest) 
	{
		 if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event"+"\n");
			}
		
		 else if (aRequest.getStatus().equalsIgnoreCase("closed"))
			{
				System.out.println("The request has already been handled"+"\n");
			}
		
		 else 
			{
				//if it is a request to add the event, the employee makes its 
			 	//status not-approved
					if (aRequest.getType().equalsIgnoreCase("register"))
						{
							aRequest.handleRequest(this, "The event is NOT approved");
							aRequest.getAnEvent().setStatus("not-approved");
						}
				
				//if it is a request for deletion, it closes the request but no
				//changes are made to the status of the event
					else if (aRequest.getType().equalsIgnoreCase("delete"))
						{
							aRequest.handleRequest(this, "The deletion of the event is NOT approved");
						}
					
					
			System.out.println("You have NOT approved to " + aRequest.getType()
						+" the following event: " + aRequest.getAnEvent().getTitle()+"\n");
			}
	}
	 
	
	
	/*
	 * An Employee can delete any event with no request needed, just by calling
	 * this method. 
	 * 
	 * If an approvalRequest is made by the Organizer, this
	 * method is called by the acceptRequest method.
	 * 
	 */
	public void deleteEvent(Event anEvent)	
	{
		anEvent.setStatus("deleted");
		System.out.println("You have deleted the following event: "+ anEvent.getTitle()+"\n");
	}
	

	
}