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
		 * if an Event is awaiting approval. The employee utilizes that to 
		 * register the Event.
		 * 
		 */
			if (aRequest == null)
			{
				System.out.println("The organizer hasn't made a request for the event");
			}
			else if(aRequest.getStatus().equals("open"))
			{
				aRequest.handleRequest(true, this, "The event is now approved");
				System.out.println("You have given permission to " +aRequest.getType()+
						" the event.");
//			Nomizw o Employee apla kanei approve to request, meta to ftiaxnei
//			h to diagrafei o organizer	
//			EventManager.getInstance().registerEvent(aRequest.getAnEvent());
			}
			else
			{
				System.out.println("The request has already been handled");
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
	{
		if (EventManager.getInstance().getEvents().contains(anEvent))
		{
			EventManager.getInstance().getEvents().remove(anEvent);
			System.out.println("The event " +anEvent+ " has been deleted");
			anEvent.setStatus("cancelled");
		}
		else
		{
			System.out.println("The event is not in the list of"
					+ "registered events!");
		}
	}
	

	
}