/*
 * Exei lista me ola ta Events.
 * Edw mporw na kanw search, 
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventManager {
	private ArrayList<Event> events= new ArrayList<>();
    private static EventManager instance;
	
    
    public EventManager(ArrayList<Event> events) {
		this.events = events;
	}

	public void addEvent(Event anEvent)
    {   	
    	events.add(anEvent);
    	//adds an event to the list, no matter the status of the event
	}
	
	
	/*
	 * isws 2 deleteEvent? Mia na tin kalei o organizer
	 * kai mia na tin kalei o ypallilos?
	 * 
	 * Giati o upallilos mporei na svisei apla ena event
	 * enw o organizer prepei na xei approval
	 */
    
    public void deleteEvent(Event anEvent, User caller) 
    {
    	 if (events.contains(anEvent))
    	 {
    		 if (caller instanceof Employee)
    		 {
    			 events.remove(anEvent);
    			 anEvent.setStatus("cancelled");
    			 System.out.println("The event " +anEvent+ " has been deleted");
    		 }
    		 else if (caller instanceof Organizer)
    		 {
    			
    			 /*
    			 * if the approvalRequest for it's
    			 * deletion has been approved, then delete it
    			 */
    		 }
    		 else
    			 System.out.println("You have no right to make"
    			 		+ "changes to events!");
    		 
    	 }
    	 else
    		 System.out.println("The event is not even registered,"
    		 		+ "so it can't be deleted!");
    	 
    }
    
 
 //NA TH FTIAKSW NA EPISTREFEI LISTA
    // EPISIS NA KANW NA PAIRNW INT DAY, MONTH, YEAR
   public List<Events> findEvents(LocalDateTime date, String location, String theme)
    {
    	
	  for (Event i : events)
    	 {
    		 if (i.getDate().equals(date) && i.getLocation().equals(location)
    				 && i.getTheme().equals(theme))
    		 {
    			return i;
    		 }
    	 }
	  
    }



	public static EventManager getInstance() {
		return instance;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}
	
	


}
