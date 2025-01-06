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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	    
    
    /* Constructor.
     * visitor: the visitor who makes the reservation to the event.
     * event: the event for which the reservation is being made.
     */
	
    public Visitor(String name, String surname, String email) {
        super(name, surname);  // Calls the constructor of the class User
        
        this.email = email; //anathetei thn timh tou email
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
            
    /* Adds a reservation for the visitor by calling the Event's addReservation method */
    public void addReservationVisitor(Event event) {
        event.addReservation(this); // Calls the method addReservation(Visitor visitor) of the class Event
    }

    /* Removes a reservation for the visitor by calling the Event's removeReservation method */
    public void removeReservationVisitor(Event event) {
        event.removeReservation(this); // Calls the method removeReservation(Visitor visitor) of the class Event
    }
             
 
}

