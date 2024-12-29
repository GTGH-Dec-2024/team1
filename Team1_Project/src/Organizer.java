import java.util.ArrayList;

public class Organizer extends User{

	private String afm;
	private String description;
	
	private ArrayList<Event> events;

	public Organizer(String name, String surname, String afm, String description, ArrayList<Event> events) {
		super(name, surname);
		this.afm = afm;
		this.description = description;
		this.events = new ArrayList<Event>();
	}
	
	public Event createEvent(String title, String theme, String description, String location, int maxCapacity, int day, int month, int year, int hour, int minutes, int duration) {
		Event event = new Event(title, theme, description, location, maxCapacity, day, month, year, hour, minutes, duration, this);
		events.add(event);
		EventManager.getInstance().addEvent(event);
		return event;
	}
	
	
	private void registerRequest(Event anEvent)
	{
		/*
		 * Ftiaxnei antikeimeno approvalRequest,
		 * kanei aithsh gia na ginei kappoio event
		 * 
		 */
		
		
	}
	
	
	public void removalRequest(Event anEvent)
	{
		/*
		 * Kanei ena approvalRequest gia diagrafi
		 * kapoiou event
		 */
	}
	/*
	 * Isws registerRequest kai removalRequest na ginoun mia
	 * klasi kai analoga me to an thelw creation i deletion
	 * na pernaei san parametros sthn approvalRequest? 
	 * (logika me to type sthn approvalRequest)
	 * 
	 */
	
	
	public void seeParticipants()
	{
		/*
		 * Blepei tous participants se ola ta event tou
		 * 
		 * 
		 * opws k na xei prepei kapou na exw mia lista me ta
		 * reservations (stin Event?? h se alli klasi??)
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
