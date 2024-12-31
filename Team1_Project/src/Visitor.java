import java.util.ArrayList;
import java.util.List;

public class Visitor extends User {
	


    private List<Reservation> reservations;

	
    public Visitor(String name, String surname, String email) {
        super(name, surname, email);  
        this.reservations = new ArrayList<>();
    }

   
    public List<Event> searchEvents(EventManager eventManager, String location, String theme, 
                                    int day, int month, int year, int hour) {
        return eventManager.searchEvents(location, theme, day, month, year, hour);
    }
    
    
    
   
    
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