package com.team1.eventproject.services;

public class EventServices {
	private ArrayList<Event> events;
    private static EventManager instance;
	
    
    public EventManager() {
    	events = new ArrayList<>();
	}

	
    //adds an event to the list, no matter the status of the event	
    public void addEvent(Event anEvent)
    {   	
    	events.add(anEvent);
	}
	

	
//returns a list of the events that meet the given criteria
   public ArrayList<Event> findEvents(int day,int month, int year, String location, String theme)
    {
	   
	   ArrayList<Event> foundEvents = new ArrayList <>();
	 
	   for (Event i : events)
	   	{
    		 	if (i.getDay()== day && i.getMonth()== month && i.getYear()== year && 
    				 i.getLocation().equalsIgnoreCase(location) && i.getTheme().equalsIgnoreCase(theme)
    				 && i.getStatus().equalsIgnoreCase("approved") && i.getCurrentCapacity()>0)
    		 		{
    			
    		 			foundEvents.add(i);
    		 		}
    	 }
	   		
	   return foundEvents;
	  
	  
    }



	public static EventManager getInstance() {
		if (instance == null) {
            instance = new EventManager();
        }
        return instance;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}
	
	


}
