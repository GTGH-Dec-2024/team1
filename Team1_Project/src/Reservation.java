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
	// Static list which stores all the reservations
	private static final List<Reservation> allReservations = new ArrayList<>();
    
    // final in the list means that the list can change but the reference of the list cannot change(can't be changed with another list)
	
	//EVA - Nomizw de xreiazetai kanena apo auta na einai final 
	
	//EVA - Pistevw thelei klasi reservationManager poy na krataei lista me ta 
	//reservations. Gia na mi mplekoume px me static k na ginontai ekei kapoies
	//leitourgies. Etsi, edw na exoume apla ta stoixeia ths kathe kratisis kai
	//sth reservationList na exoyme mia lista me oles tis kratiseis. Ekei mporoume 
	//na epistrefoume k diafora.
	
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
    
    //EVA -  Isws xreiastoume kai mia override tis toString gia ektipwsi??
}



