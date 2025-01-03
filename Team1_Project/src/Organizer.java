import java.time.LocalDateTime;
import java.util.ArrayList;

public class Organizer extends User {

	private String afm;
	private String description;
	private ArrayList<Event> events;

	public Organizer(String name, String surname, String afm, String description, ArrayList<Event> events) {
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
	 * Method getEventParticipants(): This method creates an Array-List<String> in
	 * which stores all the participants (=visitors) involved in each event of an
	 * organizer. In the method a check is taking place at first for every event so
	 * as to make sure that there are visitors or not. Afterwards, it returns the
	 * participants for every event he owns.
	 */
	public ArrayList<String> getEventsParticipants() {
		ArrayList<String> visitorsPerEvents = new ArrayList<>();
		for (Event event : events) {
			StringBuilder eventInfo = new StringBuilder("Event: " + event.getTitle());
			ArrayList<Visitor> visitors = event.getVisitors();
			if (events.isEmpty()) {
				eventInfo.append("\nNo visitors in this event yet!");
			} else {
				for (Visitor visitor : visitors) {
					eventInfo.append(
							"\nVisitor: " + visitor.getName() + " " + visitor.getSurname() + " (" + visitor.getEmail()+")");
				}
			}
			visitorsPerEvents.add(eventInfo.toString());
		}
		return visitorsPerEvents;
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
