/* The class Visitor represents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */

/* The class Visitor inherits the abstract class User and he has also an email. */
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Visitor extends User {
	
    private String email;

    private List<Reservation> reservations; // List that with objects of the class Reservation
    // The list Reservation is the storing area where each Visitor can store his reservations.
    
	// Constructor
    public Visitor(String name, String surname, String email) {
        super(name, surname);  // Calls the constructor of the class User
        this.reservations = new ArrayList<>(); //arxikopoiei thn lista krathsewn
        this.email = email; //anathetei thn timh tou email
    }
    
   /* The public method  searchEvents of the Visitor class calls the
    * findEvents method of the class EventManager. */
    // ???einai ok??? Allaksame thn searchEvent tou EventManager se findEvent?
    public void searchEvent(LocalDateTime date, String location, String theme) {
    	// Prepei na dimiourgithei antikeimeno eventmanager gia na borw na kalw ton EventManager
        Event event = EventManager.findEvent(date, location, theme);  // Calls the findEvent method of the EventManager
        if (event != null) {
            System.out.println("Event found:");
            System.out.println("Location: " + event.getLocation());
            System.out.println("Theme: " + event.getTheme());
            System.out.println("Date and Time: " + event.getDay());
        } else {
            System.out.println("No event found matching the criteria.");
        }
    }
     
            
    // The Visitor can make a reservation only if the event is approved
    public void makeReservation(Event event) {
        // Checks if the events status is "approved" // An thelw borw na xrhsimopoihsw kai streams gia ton elegxo tou status
        if (!"approved".equalsIgnoreCase(event.getStatus())) {
            System.out.println("Reservation not allowed. Event status is not approved.");
            return; // Stops the event is the event is not approved // xrisimopoiw return gia na termatisw ton elegxo
        }

        // Checks if there is already a reservation for this event.
        boolean alreadyReserved = reservations.stream()
                .anyMatch(reservation -> reservation.getEvent().equals(event));

        if (alreadyReserved) {
            System.out.println("Reservation already exists.");
            return;
        }

        // Makes and adds a new reservation
        Reservation newReservation = new Reservation(this, event);
        reservations.add(newReservation);
        System.out.println("Reservation successful: " + event.getTitle());
    }
    
    // Cancels a reservation for the Event
    public void cancelReservation(Event event) {
        // Creates object Reservation for check
        Reservation tempReservation = new Reservation(this, event);

        // Checks if the reservation already exists
        if (reservations.contains(tempReservation)) {
            reservations.remove(tempReservation);
            System.out.println("Reservation for the event: " + event.getTitle() + " canceled.");
        } else {
            System.out.println("Reservation for the event: " + event.getTitle() + " not found.");
        }
    }
    
    // an den xreiazetai tha tin diagrapsw
    // A list which stores and returns all the reservations of the Visitor
    public List<Reservation> getReservations() {
        return reservations;
    }
        
 
}