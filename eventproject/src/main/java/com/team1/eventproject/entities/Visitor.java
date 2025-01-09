package com.team1.eventproject.entities;

public class Visitor {
	private String email;
    
    public Visitor(String name, String surname, String email) {
        super(name, surname);  // Calls the constructor of the superclass User 
        this.email = email;
    }
    
    public void makeReservation(Event anEvent) 
    {
    	ReservationManager.getInstance().createReservation(anEvent, this);
    }
	
	
    public void cancelReservation(Event anEvent) 
    {
    	ReservationManager.getInstance().removeReservation(anEvent, this);
	}
	
    
  
  public void showMyEvents()
  {
  	ArrayList<Event> visitorEvents = ReservationManager.getInstance().getEventsForVisitor(this);
	  
	  if (visitorEvents.isEmpty())
	  {
	  	System.out.println("There are no reservations for " +name+ " " +surname +"!");
	  }
	  else
	  {
		System.out.println("The reservations for " +name+ " " +surname +"are:");
	  	for (Event event: visitorEvents)
	  	{
	  		System.out.println(event.getTitle());
	  	}
	  }
  }

    
    
    /* The public method  searchEvents of the Visitor class calls the
    * findEvents method of the class EventManager. */    
    
    public void searchEvent(int day, int month, int year, String location, String theme) {
        // make or retrieve object EnentManager
        EventManager eventManager = EventManager.getInstance();

        // Call method findEvents through the object
        ArrayList<Event> events = eventManager.findEvents(day, month, year, location, theme);
        
        // Checks if such events were found
        if (events.isEmpty()) {
            System.out.println("No events found matching the criteria.");
        } else {
            // Show results of the events found
            System.out.println("Events found:");
            for (Event event : events) {
                System.out.println("Event Title: " + event.getTitle());
                System.out.println("Location: " + event.getLocation());
                System.out.println("Theme: " + event.getTheme());
                System.out.println("Date and Time: " + event.getDate() + " " + event.getTime());
                System.out.println("-------------------------------");
            }
        }
    }  
    

	public String getEmail() 
	{
		return email;
	}

	@Override
	public String toString()
	{
		return name + " " +surname;
	}
 

}
