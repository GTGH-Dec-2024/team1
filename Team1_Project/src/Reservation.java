
import java.util.Date;

public class Reservation {
	private Visitor visitor;  
    private Event event;      
 
  
    
    public Reservation(Visitor visitor, Event event) {
        this.visitor = visitor;
        this.event = event;
        
    }

    
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

 
    public void displayReservationDetails() {
        System.out.println("Reservation Details:");
        System.out.println("Visitor: " + visitor.getName() + " " + visitor.getSurname());
        System.out.println("Event: " + event.getTitle());
        System.out.println("Theme: " + event.getTheme());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Location: " + event.getLocation());
        // ???System.out.println("Event Date: " + event.getDay() + "/" + event.getMonth() + "/" + event.getYear());
        // ???System.out.println("Event Time: " + String.format("%02d:%02d", event.getHour(), event.getMinutes()));
        System.out.println("Duration: " + event.getDuration() + " minutes");
        System.out.println("Max Capacity: " + event.getMaxCapacity());
    }
}



