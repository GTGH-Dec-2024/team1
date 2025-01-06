/* The class Reservation stores information about the reservation 
 * that a visitor has done for a specific event.
 */
import java.util.ArrayList;
import java.util.List;

public class Reservation {
	// The visitor who has made the reservation
	private final Visitor visitor; // connection with the class Visitor 
    // The event to which the reservation has been made
	private final Event event; // connection with the class Event
    // visitor and event must be final so that they can not change for the specific reservation
	// Static list which stores all the reservations
	private static  List<Reservation> allReservations = new ArrayList<>();
    // Nomizw prepei na einai etsi: private static final List<Reservation> allReservations = new ArrayList<>();
    // final in the list means that the list can change but the reference of the list cannot change(can't be changed with another list)
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

    //Getters and Setters for the fields Visitor and Reservation
    public Visitor getVisitor() {
        return visitor;
    }

    /*Nomizw pws den prepei na exei setter afou einai final
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
    */

    public Event getEvent() {
        return event;
    }
    
    /*Oute edw
    public void setEvent(Event event) {
        this.event = event;
    } */
     
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



