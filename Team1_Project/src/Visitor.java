/* The class Visitor represents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */

import java.util.ArrayList;

/* The class Visitor inherits the abstract class User and he has also an email. */
public class Visitor extends User {

	private String email;
	private ArrayList<Reservation> reservations;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * Constructor. visitor: the visitor who makes the reservation to the event.
	 * event: the event for which the reservation is being made.
	 */

	public Visitor(String name, String surname, String email) {
		super(name, surname); // Calls the constructor of the class User
		this.email = email; // anathetei thn timh tou email
		this.reservations = new ArrayList<>();
	}

	/*
	 * The public method searchEvents of the Visitor class calls the findEvents
	 * method of the class EventManager.
	 */
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

	public void makeReservation(Event event) {
		// Checks if the events status is "approved"
		// An thelw borw na xrhsimopoihsw kai streams gia ton elegxo tou status
		if (!"approved".equalsIgnoreCase(event.getStatus())) {
			System.out.println("Reservation not allowed. Event status is not approved.");
			return; // Stops the event if the event is not approved // xrisimopoiw return gia na
					// termatisw ton elegxo
		} else if (event.getCurrentCapacity() >= event.getMaxCapacity()) {
			System.out.println("Event is already full of reservations! Could'n make the reservation!");
		} else {
			// Create a new reservation for this visitor and event
			Reservation newReservation = new Reservation(this, event);
			// Add the reservation to the visitor's list of reservations
			reservations.add(newReservation);
			System.out.println("Reservation for the event: " + event.getTitle() + " confirmed.");
			event.addVisitorToEvent(this);
		}
	}

	/*
	 * Adds a reservation for the visitor by calling the Event's addReservation
	 * method
	 */
	/*
	 * public void addReservationVisitor(Event event) { event.addReservation(this);
	 * // Calls the method addReservation(Visitor visitor) of the class Event }
	 */

	/*
	 * Removes a reservation for the visitor by calling the Event's
	 * removeReservation method
	 */
	/*
	 * public void removeReservationVisitor(Event event) {
	 * event.removeReservation(this); // Calls the method removeReservation(Visitor
	 * visitor) of the class Event }
	 */

}
