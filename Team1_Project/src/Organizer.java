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

	public ApprovalRequest registerRequest(Event event) {
		events.add(event);
		EventManager.getInstance().addEvent(event);
		ApprovalRequest request = new ApprovalRequest("register", timestamp, this, event, "Accept the request for this event.");
		System.out.println("Request for event registration made successful.\nWaiting for employee's approval");
		return request;
	}
	
	public ApprovalRequest removalRequest(Event event) {
		
		if(events.contains(event)) {
			ApprovalRequest request = new ApprovalRequest("delete", timestamp, this, event, "Accept the request for the deletion of this event.");
			System.out.println("Request for event deletion made successful.\nWaiting for employee's approval");
			return request;
		}else {
			try{
				
			}catch() {
				
			}
		}
		
	}
	
	public void seeParticipants() {
		if(events.isEmpty()) {
			System.out.println("Zero events yet from organizer: "+this.getName()+" "+this.getSurname());
		}else {
			for(Event event : events ) {
				System.out.println("Visitors of the event: "+event.getTitle()+" ("+event.getTheme()+")");
			}
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

}
