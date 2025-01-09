package com.team1.eventproject.services;

public class ApprovelRequestServices {
private ArrayList<ApprovalRequest> allRequests;

	
	
	public ApprovalRequestManager(ArrayList<ApprovalRequest> allRequests) 
	{
		this.allRequests = new ArrayList<>();
	}

	
	public void makeRequest(Event anEvent, Organizer anOrganizer, String requestType) 
	{
		//psakse sthn EventManager an o Organizer tou anEvent einai o an Organizer
		//an oxi, bgale error. An oxi, pes tou to
		
		// An requestType.equalsIgnoreCase(delete) psakse kai an uparxei
		//to event
		
		ApprovalRequest request = new ApprovalRequest(requestType, LocalDateTime.now(), anOrganizer, anEvent,
				"A request to " +requestType + "the event: " +anEvent.getTitle());
		
		allRequests.add(request);	
		System.out.println("A request to" +requestType+ "the event: " +anEvent.getTitle()+
				"has been made.\nWaiting for an employee's approval!");
		
	}
	
	
	
	/*
	 * An employee handles a request for the registration of an event.
	 * 
	 * This method takes an Event, an Employee, and a boolean "isApproved"
	 * attribute. The isApproved is true if the Employee wants to approve
	 * the Event and false if he/she doesn't want to approve it
	 * 
	 * Depending on isApproved, the status of the Event changes, from
	 * pending to "approved" or "not-approved". The event status is
	 * updated when the request is closed by calling the closeRequest
	 * 
	 */
	public void handleRegistrationRequest(Event anEvent, Employee anEmployee, boolean isApproved)
	{
		ApprovalRequest request = this.findApprovalRequest(anEvent, "register");
		
		if (request == null)
		{
			System.out.println("The organizer of the event has not made"
					+ "a registration request for it!");
		}
			
		else if (request.getStatus().equalsIgnoreCase("closed"))
		{
			System.out.println("The registration request for this event"
					+ "has already been handled.");
		}
		
		else
		{
			if (isApproved)
			{
				request.getAnEvent().setStatus("approved");
				closeRequest(request,anEmployee, "The event " +anEvent.getTitle()+" is now approved");
				
				System.out.println("The employee " +anEmployee.getName()+ " has just approved to register "
						+ "the following event: " + request.getAnEvent().getTitle()+"\n");
			}
			else
			{
				request.getAnEvent().setStatus("not-approved");
				closeRequest(request,anEmployee, "The event " +anEvent.getTitle()+
						" is NOT approved by the employee");
				
				System.out.println("The employee " +anEmployee.getName()+ " has NOT approved to register "
						+ "the following event: " + request.getAnEvent().getTitle()+"\n");
			}
			
		}
		
			
		
	}
	
	
	public void handleDeletionRequest(Event anEvent, Employee anEmployee, boolean isApproved)
	{
		
	}
	
	
	/*
	 * A private method, it can only be called by ApprovalRequestManager
	 * when handling a request.
	 *
	 * Closes the request, updates the closetAt and handledBy fields
	 * and adds the Employee's comment, if there is one
	 * 
	 */
	
	private void closeRequest(ApprovalRequest aRequest, Employee anEmployee, String comment)
	{
		aRequest.setStatus("closed");
		aRequest.setClosedAt(LocalDateTime.now());
		aRequest.setHandledBy(anEmployee);
		
		if (!comment.isBlank()) {
		    aRequest.addComments("\nEmployee's comment: " + comment);
		}
	}

	
	public void makeRegistrationRequest(Event anEvent, Organizer anOrganizer) {
//		psakse sthn EventManager an o Organizer tou anEvent einai o an Organizer
		//an oxi, bgale error. An nai synexise
		ApprovalRequest request = new ApprovalRequest("register", LocalDateTime.now(), anOrganizer, anEvent,
				"Accept the request for this event.");
		allRequests.add(request);
		System.out.println("A request to register the event: " +anEvent.getTitle()+
				"has been made successfully.\nWaiting for an employee's approval!");
		//return request;
	}

	
	public void makeDeletionRequest(Event anEvent, Organizer anOrganizer) {

//		if ( psakse sto ArrayList tis EventManager an uparxei to event)
//				{
//					//an den uparxei...
//				return;
//				}
				
		if (!anEvent.getOrganizer().equals(anOrganizer))
		{
			System.out.println("You are not the organizer of the event,"
					+" therefore you can't make a deletion request for it");
			return;
		}
				
		ApprovalRequest request = new ApprovalRequest("delete", LocalDateTime.now(), anOrganizer, anEvent,
				"Accept the request to delete this event.");
		allRequests.add(request);
		
	}


    public ApprovalRequest findApprovalRequest(Event anEvent, String requestType) 
    {
        for (ApprovalRequest i : allRequests) {
            if (i.getAnEvent().equals(anEvent) && 
            		i.getType().equalsIgnoreCase(requestType)) {
               
            	return i;
            }
        }
        return null;
    }
}
