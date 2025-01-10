package com.team1.eventproject.entities;
/* The class Visitor represents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */

import java.util.ArrayList;

/* The class Visitor inherits the abstract class User and he has also an email. */
public class Visitor extends User {
	private String email;
	private static int counter = 1;
	private int id;
    
	// Constructor
    public Visitor(String name, String surname, String email) {
        super(name, surname); // Calls the constructor of the superclass User 
        this.id = counter++; //the id is given automatically by the program
        this.email = email;
    }
    
    // Tha metafertheu stin klasi ReservationServices
    public void makeReservation(Event anEvent) 
    {
    	ReservationManager.getInstance().createReservation(anEvent, this);
    }
	
	// Tha metaferthei stin klasi ReservationServices
    public void cancelReservation(Event anEvent) 
    {
    	ReservationManager.getInstance().removeReservation(anEvent, this);
	}
	
    
  // Tha metaferthei mallon stin klasi EventServices
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
    // Tha meteferthei stin klasi EventServices
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
		return name + surname;
	}
 

}
