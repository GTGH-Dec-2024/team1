import java.time.LocalDateTime;
import java.time.ZoneOffset;
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

	public Event registerRequest(Event event, Employee employee) {
		events.add(event);
		EventManager.getInstance().addEvent(event);
		ApprovalRequest request = new ApprovalRequest("Request for event approval.", timestamp, this, event, "Accept the request for this event.");
		employee.approveEvent(request); 
		return event;
	}

	// ok now in the same program the organizer can have the ability to delete an
	// event of theirs. First they request from the employee for the deletion and
	// after they get the ok (or the not ok) the event gets deleted (if deletion not
	// approved) from the arrayList of the eventList list.
	public void removalRequest(Event anEvent) {
		ApprovalRequest request = new ApprovalRequest("Request for event approval.", timestamp, this, event, "Accept the request for this event.");
		
	}
	/*
	 * Isws registerRequest kai removalRequest na ginoun mia klasi kai analoga me to
	 * an thelw creation i deletion na pernaei san parametros sthn approvalRequest?
	 * (logika me to type sthn approvalRequest)
	 * 
	 */

	public void seeParticipants() {
		/*
		 * Blepei tous participants se ola ta event tou
		 * 
		 * 
		 * opws k na xei prepei kapou na exw mia lista me ta reservations (stin Event??
		 * h se alli klasi??)
		 * 
		 */
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

}
