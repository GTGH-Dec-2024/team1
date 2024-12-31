import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
	
    private String email;

    private List<Reservation> reservations;

	
    public Visitor(String name, String surname, String email) {
        super(name, surname);  
        this.reservations = new ArrayList<>();
        this.email = new email;
    }

   //na thn kanw void? kai oxi lista//prepei na dimiourgisw antikeimeno eventmanager
    // gia na borw na kalw ton event manager, tha pernei day-month year
    public List<Event> searchEvents(String location, String theme, 
                                    int day, int month, int year,) {
        return eventManager.findEvents(location, theme, day, month, year, hour);
    }
    
    
    
   
    // na thn kanw void kai na epistrefei apla to minima me system out print
    // prepei na vevaiwtho oti den yparxei reservation, des me boolean true false.
    public String makeReservation(Event event) {
        
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                return "Reservation already exists.";
            }
        }

        
        Reservation newReservation = new Reservation(this, event);
        reservations.add(newReservation);
        return "Reservation successful: " + event.getTitle();
    }
    
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
    
    
    public List<Reservation> getReservations() {
        return reservations;
    }
        
 
}