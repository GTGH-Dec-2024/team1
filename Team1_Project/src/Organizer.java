import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Organizer extends User {

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
	 * This method utilizes the ReservationManager class to
	 * get information about each reservation. In a loop, it 
	 * searches for all the events made by the organizer who
	 * called the method.
	 * 
	 * Then, it finds the visitors of those events and adds them
	 * to a HashSet (so that there are no duplicates in the list of names)
	 * 
	 *
	 */
	public void getallMyEventsParticipants() {
  
		Set<Visitor> myParticipants = new HashSet<>();
		
		for(Reservation temp: ReservationManager.getInstance().getAllReservations())
    	{
    		if (temp.getEvent().getOrganizer().equals(this))
    		{
    			myParticipants.add(temp.getVisitor());
    		}
    	}
		System.out.println("The participants on events organized by" +name +" "
				+surname+" are:");
		
		if (myParticipants.isEmpty())
		{
			System.out.println("There are no participants to your events");
			return;
		}
			
		for (Visitor aVisitor : myParticipants) 
		{
		    System.out.println(aVisitor.getName() + " " + aVisitor.getSurname());
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
