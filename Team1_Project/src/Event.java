import java.util.ArrayList;
import java.util.List;
public class Event {
	private String title;
	private String theme;
	private String description;
	private String location;
	private int maxCapacity;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int duration;
	private Organizer organizer;
	private String status; // status = "pending" or "approved" or "not-approved" or "deleted" 
	private int currentCapacity;
	private ArrayList<Visitor> visitors; // new list for the visitors
	

	public Event(String title, String theme, String description, String location, int maxCapacity, int day, int month,
			int year, int hour, int minutes, int duration, Organizer organizer) {
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.location = location;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = 0;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.organizer = organizer;
		this.status = "Pending";
		this.visitors = new ArrayList<>(); //a new visitors list for each event
		
	}
	
    // H klasi Event borei na xrisimopoiisei ton constructor tis klasis Reservation gia na dimiourgisei kai na apothikeusei automata tis kratiseis
	public void addReservation(Visitor visitor) {
	    // Check if the status of the event is approved
		if (!"approved".equalsIgnoreCase(this.status)) {
	        System.out.println("Cannot create reservation: Event is not approved.");
	        return;
	    }

	    // Check if there is enough Capacity
	    if (this.currentCapacity >= this.maxCapacity) {
	        System.out.println("Cannot create reservation: Event is fully booked.");
	        return;
	    }

	    // Creates a new reservation which is stored automatically to the list allReservations through the constructor of the class Reservation
	    new Reservation(visitor, this);

	    // Increases the current capacity
	    this.currentCapacity++;

	    // Success message
	    System.out.println("Reservation successfully created for visitor: " 
	        + visitor.getName() + " " + visitor.getSurname());
	}
	
	
	

	public void removeReservation(Visitor visitor) {
	    // Find the reservation
	    for (Reservation reservation : Reservation.getAllReservations()) {
	        if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(this)) {
	            // Remove reservation from the list
	            Reservation.removeReservation(reservation);

	            // Decrease Capacity
	            this.currentCapacity--;

	            // Success message
	            System.out.println("Reservation removal for the event " + this.getTitle() 
	                + " made successful for visitor " + visitor.getName() + " " + visitor.getSurname());
	            return;
	        }
	    }

	    // If the reservation is not found
	    System.out.println("Reservation removal failed! Visitor seems not to participate in this event.");
	}
	
	// Returns the visitors list of the current Event
    public ArrayList<Visitor> getVisitors() {
        return visitors;
    }
	
/*	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

*/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDate() {
		return String.format("%02d/%02d/%04d", day, month, year);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getTime() {
		return String.format("%02d:%02d", hour, minutes);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	


	@Override
	public String toString() {
		return "Event [title = " + title 
				+ ", theme = " + theme 
				+ ", description = " + description 
				+ ", location = " + location 
				+ ", maxCapacity = " + maxCapacity 
				+ ", currentCapacity = " + getCurrentCapacity()+"/"+getMaxCapacity()
				+ ", date = " + getDate() 
				+ ", time = " + getTime()
				+ ", duration = " + duration 
				+ ", organizer = " + organizer.getName()
				+ ", status = " + status
				+ ", currentCapacity = " + currentCapacity 
				/*+ ", reservations = " + reservations*/ + "]";
	} 

}


