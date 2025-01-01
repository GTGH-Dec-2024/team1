/* The class Visitor rempresents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */

/* The class Visitor inherits the abstract class User and he has also an email. */
import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
	
    private String email;

    private List<Reservation> reservations; // List that with objects of the class Reservation
    // The list Reservation is the storing area where each Visitor can store his reservations.
    
	// Constructor
    public Visitor(String name, String surname, String email) {
        super(name, surname);  // Calls the constructor of the class User
        this.reservations = new ArrayList<>();
        this.email = new email;
    }
   /* The public method  searchEvents of the Visitor class calls the
    * findEvents method of the class eventManager. */
   //na thn kanw void? kai oxi lista//prepei na dimiourgisw antikeimeno eventmanager
    // gia na borw na kalw ton event manager, tha pernei day-month year
    public List<Event> searchEvents(String location, String theme, 
                                    int day, int month, int year) {
        return eventManager.findEvents(location, theme, day, month, year, hour);
    }
    
    
    
   
    // na thn kanw void kai na epistrefei apla to minima me system out print
    // prepei na vevaiwtho oti den yparxei reservation, des me boolean true false.
    public String makeReservation(Event event) {
        //Elegxos an uparxei hdh krathsh gia thn idia ekdilwsi
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                return "Reservation already exists.";
            }
        }

        //Dimiourgia kai prosthiki kratisis
        Reservation newReservation = new Reservation(this, event);
        reservations.add(newReservation);
        return "Reservation successful: " + event.getTitle();
    }
    
    //akurwnei mia kratisi gia tin ekdilwsi
    //tha tin kanw kai aythn void, des methodo contains
    public String cancelReservation(Event event) {
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                reservations.remove(reservation);
                return "Reservation for the event: " + event.getTitle() + " canceled.";
            }
        }
        return "reservation for event not found: " + event.getTitle();
    }
    
    // an den xreiazetai tha tin diagrapsw
    // A list which stores and returns all the reservations of the Visitor
    public List<Reservation> getReservations() {
        return reservations;
    }
        
 
}