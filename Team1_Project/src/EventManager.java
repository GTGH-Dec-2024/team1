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

	public void registerEvent(Event anEvent)
    {   	
    	/*
    	 * Called by the Organizer. Checks if the event has been approved
    	 * (checks the event-status) and if yes it adds it to the list
    	 * 
    	 */
		switch (anEvent.getStatus()) {
	    case "approved":
	        events.add(anEvent);
	        System.out.println("The event has been registered successfully!");
	        break;
	    case "not-approved": 
	        System.out.println("The event cannot be registered because it "
	        		+ "was not approved.");
	        break;     
	    case "created":
	        System.out.println("The event cannot be registered. You need to"
	        		+ "make an approval request first");
	        break;
	    case "pending":
	        System.out.println("The event cannot be registered. It is awaiting"
	        		+ "approval from an Employee");
	        break;  
	    case "cancelled":
	        System.out.println("The event has been cancelled and therefore"
	        		+ "cannot be registered.");
	        break;    
	    
		}
	}
    
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
    
 
    
   public Event searchEvent(LocalDateTime date, String location, String theme)
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
