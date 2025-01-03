/* The class Visitor rempresents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */


/* The class Visitor inherits the abstract class User and he has also an email. */
import java.util.ArrayList;
import java.util.List;


/* The class Visitor inherits the abstract class User and he has also an email. */
public class Visitor extends User {
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
    private List<Reservation> reservations; // List with objects of the class Reservation
    // The list Reservation is the storing area where each Visitor can store his reservations.
    
    
    
    /* Constructor.
     * visitor: the visitor who makes the reservation to the event.
     * event: the event for which the reservation is being made.
     */
	
    public Visitor(String name, String surname, String email) {
        super(name, surname);  // Calls the constructor of the class User
        this.reservations = new ArrayList<>(); //arxikopoiei thn lista krathsewn
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
            
    // The Visitor can make a reservation only if the event is approved
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