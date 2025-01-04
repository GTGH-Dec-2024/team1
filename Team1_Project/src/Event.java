import java.util.ArrayList;

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
	private ArrayList<Visitor> visitors;
	private ArrayList<Reservation> reservations;

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
		this.visitors = new ArrayList<>();
	}

	public void addReservation(Reservation aReservation) {
		if ("Approved".equalsIgnoreCase(this.status) && reservations.size() < maxCapacity) {
			reservations.add(aReservation);
			currentCapacity++;
			System.out.println("Reservation for event " + this.getTitle() + " added successlly!\nThank you visitor "
					+ aReservation.getVisitor().getName() + " " + aReservation.getVisitor().getSurname());
		} else if (!"Approved".equalsIgnoreCase(this.status)) {
			System.out.println("Event not approved!\nYou can not make a reservation to a not approved event!");
		} else {
			System.out.println(
					"You can not make a reservation to this event!\nThe capacity of the event's reservation is full!");
		}
	}

	public void removeReservation(Reservation aReservation) {
		boolean exists = reservations.stream()
				.anyMatch(r -> r.getVisitor().equals(aReservation.getVisitor()) && r.getEvent().equals(this));

		if (exists) {
			reservations.remove(aReservation);
			currentCapacity--;
			System.out.println("Reservation removal for the event " + aReservation.getEvent().getTitle()
					+ " made successful visitor " + aReservation.getVisitor().getName() + " "
					+ aReservation.getVisitor().getSurname());
		} else {
			System.out.println("Reservation removal failed!\nVisitor seems not to participate in that event!");
		}
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

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

	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}

	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
	}

	public void removeVisitor(Visitor visitor) {
		visitors.remove(visitor);
	}

	public ArrayList<Visitor> getVisitors() {
		return visitors;
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
				+ ", reservations = " + reservations + "]";
	}

}
