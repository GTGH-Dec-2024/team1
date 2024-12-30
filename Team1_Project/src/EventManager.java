/*
 * Exei lista me ola ta Events.
 * Edw mporw na kanw search, 
 */

import java.util.ArrayList;

public class EventManager {
	private ArrayList<Event> events;
    private static EventManager instance;
	
	
    public void registerEvent(Event anEvent)
    {   	
    	//no check necessary, called by the employee 
    	events.add(anEvent);
    }
    
    public void deleteEvent(Event anEvent, User caller) 
    {
    	 if (events.contains(anEvent))
    	 {
    		 if (caller instanceof Employee)
    		 {
    			 events.remove(anEvent);
    			 anEvent.setStatus("cancelled");
    		 }
    		 else if (caller instanceof Organizer)
    		 {
    			
    			 /*
    			 * if the approvalRequest for it's
    			 * deletion has been approved then delete it
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
    
 /*   public Event searchEvent()
    {
    	*
    	 * Nomizw pairnei imerominia, thema klp
    	 * kai epistrefei antikeimeno typou event(?)
    	 
    }
*/

	public static EventManager getInstance() {
		return instance;
	}


}
