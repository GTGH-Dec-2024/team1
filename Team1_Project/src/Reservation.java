/* The class Reservation stores information about the reservations 
 * that the Visitors make to the events.
 */
import java.util.ArrayList;
import java.util.List;

public class Reservation {
	
	// The visitor who has made the reservation
	private final Visitor visitor; // connection with the class Visitor 
    // The event to which the reservation has been made
	private final Event event; // connection with the class Event
    // visitor and event must be final so that they can not change for the specific reservation
	
	
    /* Constructor.
     * visitor: the visitor who makes the reservation to the event.
     * event: the event for which the reservation is being made.
     */
    public Reservation(Visitor visitor, Event event) {
        this.visitor = visitor;
        this.event = event;
        
        // save reservation in the list     
        allReservations.add(this);
    }

    
    public static List<Reservation> getAllReservations() {
		return allReservations;
	}
    
    // Remove reservation method
    public static void removeReservation(Reservation reservation) {
        allReservations.remove(reservation);
    }
    //Getters and Setters for the fields Visitor and Reservation
    public Visitor getVisitor() {
        return visitor;
    }


    public Event getEvent() {
        return event;
    }
     
   
    // Method which shows the information of the reservation.
    public void displayReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Visitor: " + visitor.getName() + " " + visitor.getSurname());
        System.out.println("Event: " + event.getTitle());
        System.out.println("Theme: " + event.getTheme());
        System.out.println("Location: " + event.getLocation());
        System.out.println("Event Date: " + event.getDay() + "/" + event.getMonth() + "/" + event.getYear());
        System.out.println("Event Time: " + event.getHour() + "/" + event.getMinutes());        
           }
}


