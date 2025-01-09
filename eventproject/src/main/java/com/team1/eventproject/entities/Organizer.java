package com.team1.eventproject.entities;

public class Organizer {
	private String afm;
	private String description;
	private ArrayList<Event> events;

	public Organizer(String name, String surname, String afm, String description) {
		super(name, surname);
		this.afm = afm;
		this.description = description;
		this.events = new ArrayList<Event>();
	}

	LocalDateTime timestamp = LocalDateTime.now();

	/*
	 * Method registerRequest(): This method takes as a parameter an event that the
	 * organizer want to request for its approval from the employee. First, he
	 * "adds" it into his events list but also into the total events list of the
	 * municipality of the city (regardless if the event will be approved or not
	 * from the employee). Afterwards, it creates an ApprovalRequest object with
	 * type --> "make" so as to indicate the employee to accept/approve the event.
	 * At last the method returns the request so as the employee can handle it later
	 * on in the main function.
	 */
	public ApprovalRequest registerRequest(Event event) {
		events.add(event);
		EventManager.getInstance().addEvent(event);
		ApprovalRequest request = new ApprovalRequest("register", timestamp, this, event,
				"Accept the request for this event.");
		System.out.println("Request for event registration made successful.\nWaiting for employee's approval!");
		return request;
	}

	/*
	 * Method removalRequest(): This method takes a parameter an event that the
	 * organizer wants to request for its deletion from the employee. First, a check
	 * is made so as to make sure that this event is for sure an organizer's event.
	 * If the event belongs to the organizer then a request object of type
	 * ApprovalRequest is made with type --> "delete" so as to indicate the employee
	 * to delete the event(the deletion procedure will not remove the event from
	 * both lists, but it will change its status to "deleted"). At last the method
	 * returns the request so as the employee can handle it later on in the main
	 * function.
	 */
	public ApprovalRequest removalRequest(Event event) {

		if (events.contains(event)) {
			ApprovalRequest request = new ApprovalRequest("delete", timestamp, this, event,
					"Accept the request for the deletion of this event.");
			System.out.println("Request for event deletion made successful.\nWaiting for employee's approval!");
			return request;
		} else {
			System.out.println(
					"The event you entered for deletion does not belong to you!\nDeletion request can not be created!");
			return null;
		}

	}

	/*
	 * This method utilizes the EventManager and ReservationManager classes 
	 *
	 * First, it finds all the Events made by the organizer
	 * 
	 * Then, for each event, it checks the allReservations ArrayList
	 * to find the names of the people who have made a reservation for them
	 *
	 */
	public void showMyEventParticipants() {
		   
		System.out.println("The participants of events organized by " + name + " " + surname + " are:");

		  
			boolean hasEvents = false; //used at the end - if the organizer has no events, it will print a message

		    for (Event event : EventManager.getInstance().getEvents()) 
		    { 
		       // The only events that can have reservations are the ones that have been approved
		    	if (event.getOrganizer().equals(this) && event.getStatus().equalsIgnoreCase("approved")) 
		        {
		            hasEvents = true;
		            StringBuilder eventInfo = new StringBuilder("Event: " + event.getTitle());

		            ArrayList<Visitor> eventVisitors = new ArrayList<>();
		            
		            for (Reservation temp : ReservationManager.getInstance().getAllReservations()) 
		            {
		                if (temp.getEvent().equals(event)) 
		                {
		                	eventVisitors.add(temp.getVisitor());
		                }
		            }

		            if (eventVisitors.isEmpty()) 
		            {
		                eventInfo.append("\nNo visitors in this event yet!");
		            } else 
		            
		            {
		                for (Visitor visitor : eventVisitors) 
		                {
		                    eventInfo.append("\nVisitor: " + visitor.getName() + " " + visitor.getSurname() + " (" + visitor.getEmail() + ")");
		                }
		            }

		            System.out.println(eventInfo.toString());
		        }
		    }

		    //if the organizer hasnt made any events yet
		    if (!hasEvents) 
		    {
		        System.out.println("There are no events organized by " + name + " " + surname);
		    }
		}
	
	

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}

	@Override
	public String toString() {
		return "Organizer [afm=" + afm + ", description=" + description + ", events=" + events + "]";
	}

}
