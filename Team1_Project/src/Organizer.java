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
	
	/*
	 * Methodos createEvent, tin kalei o organizer
	 * kai ftiaxnei to Event, to opoio den tha einai approved
	 * akoma
	 * 
	 * kalei automata th registerRequest??
	 */
	
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
	
	
	
	

}
