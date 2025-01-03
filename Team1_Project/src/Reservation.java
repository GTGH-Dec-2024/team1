/* The class Reservation stores information about the reservation 
 * that a visitor has done for a specific event.
 */

public class Reservation {
	// The visitor who has made the reservation
	private Visitor visitor; // connection with the class Visitor 
    // The event to which the reservation has been made
	private Event event; // connection with the class Event
 
  
    /* Constructor.
     * visitor: the visitor who makes the reservation to the event.
     * event: the event for which the reservation is being made.
     */
    public Reservation(Visitor visitor, Event event) {
        this.visitor = visitor;
        this.event = event;
        
    }

    //Getters and Setters for the fields Visitor and Reservation
    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
     
    // Method which shows the information of the reservation.
    // Theloume na emfanizei kai kati allo??
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



