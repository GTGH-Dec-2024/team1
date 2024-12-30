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
    
    public void deleteEvent(Event event) 
    {
        /*
         * An to event uparxei sti lista me ta events
         * svisto
         * 
         * PREPEI NA DIAXWRISW AN TO KANEI O ORGANIZER
         * (KAI DHLADH THA THELEI NA XEI APPROVAL) H
         * O IPALLILOS
         * 
         * Kati vrika online oti isws ginetai me StackTrace 
         * kai streams klp klp, wste na doume poios tin kalese
         */
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
