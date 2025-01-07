import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		// Dimiourgia 10 Visitors

		// Lista episkephtwn
		List<Visitor> visitors = new ArrayList<>();

		// Dimiourgia episkephtwn
		Visitor visitor1 = new Visitor("Giorgos", "Papadopoulos", "visitor1@gmail.com");
		visitors.add(visitor1);

		Visitor visitor2 = new Visitor("Maria", "Ioannidou", "visitor2@gmail.com");
		visitors.add(visitor2);

		Visitor visitor3 = new Visitor("Kostas", "Alexiou", "visitor3@gmail.com");
		visitors.add(visitor3);

		Visitor visitor4 = new Visitor("Panagiotis", "Konstantinidis", "visitor4@gmail.com");
		visitors.add(visitor4);

		Visitor visitor5 = new Visitor("Katerina", "Antoniou", "visitor5@gmail.com");
		visitors.add(visitor5);

		Visitor visitor6 = new Visitor("Nikos", "Papakonstantinou", "visitor6@gmail.com");
		visitors.add(visitor6);

		Visitor visitor7 = new Visitor("Maria", "Oikonomidou", "visitor7@gmail.com");
		visitors.add(visitor7);

		Visitor visitor8 = new Visitor("Nikos", "Pantelidis", "visitor8@gmail.com");
		visitors.add(visitor8);

		Visitor visitor9 = new Visitor("Anastasia", "Georgiou", "visitor9@gmail.com");
		visitors.add(visitor9);

		Visitor visitor10 = new Visitor("Dimitra", "Aggelidou", "visitor10@gmail.com");
		visitors.add(visitor10);

		Employee em1 = new Employee("Maria ", "Papadopoulou", "maria.papadop@gmail.com");
		Employee em2 = new Employee("Dimitris ", "Konstantinidis", "dimitris.kons@gmail.com");
		Employee em3 = new Employee("Giorgos  ", "Nikolaidis", " giorgos.nikolaid@example.com");

		Organizer o1 = new Organizer("George", "Georgidis", "A111", "An organizer that likes events");
		Organizer o2 = new Organizer("Maria", "Maraki", "A222", " ");
		Organizer o3 = new Organizer("Petros", "Petridis", "A333", "The best organizer");
		Organizer o4 = new Organizer("Zoe", "Zoedi", "A444", "A new event organizer!");

		Event ev1 = new Event("Cultural Night of Thessaloniki", "Performance",
				" A mesmerizing evening filled with traditional dances, music, and flavors of Macedonia, featuring local dance groups and musical ensembles.",
				"Aristotelous Square", 5, 15, 6, 2025, 20, 00, 3, o1);

		Event ev2 = new Event("Thessaloniki Taste Festival", "Food",
				"A journey into the flavors of Thessaloniki, with exhibitions of local products, cooking demonstrations, and tastings by renowned chefs.",
				"Nikis Avenue Pedestrian Zone", 3, 10, 9, 2025, 18, 00, 4, o2);

		Event ev3 = new Event("Ice skating theater", "Performance",
				"The best dancers from all over the world meet for one huge performance.", "Venizelou 20", 7, 17, 3,
				2025, 20, 00, 2, o3);

		Event ev4 = new Event("The most magical show", "Performance",
				"A famous magician will shock you with her tricks", "Vasilisis Olgas 104", 5, 30, 5, 2025, 21, 00, 2,
				o4);

		Event ev5 = new Event("The three best friends", "Theater for kids",
				"This play is full of messages about the value of friendship!", "Egnatias 45", 10, 9, 8, 2025, 18, 00,
				2, o4);

		Event ev6 = new Event("Thessaloniki Marathon Festival", "Sport",
				"A city-wide marathon event that welcomes runners of all levels to explore Thessaloniki s landmarks while promoting a healthy and active lifestyle. Includes live music and food stands.",
				"Start and finish at White Tower Square", 15, 20, 10, 2025, 21, 30, 6, o1);

		Event ev7 = new Event("Open-Air Cinema Night", "Cinema",
				"A relaxing evening of cinema in an open-air setup, featuring classic movies on a giant screen. Bring your blanket and enjoy popcorn under the night sky.",
				"Seikh Sou Forest Park", 5, 12, 7, 2025, 20, 30, 3, o2);

		Event ev8 = new Event("Thessaloniki Tech Fair", "Technology",
				"An exhibition of cutting-edge technologies and startups, with hands-on demonstrations, networking opportunities, and guest speakers from the tech world.",
				"Thessaloniki International Fairgrounds (TIF HELEXPO)", 6, 15, 11, 2025, 15, 30, 8, o3);

		/*
		 * //Organizers are creating approval requests for their events ApprovalRequest
		 * registerRequest1 = o3.registerRequest(ev3); ApprovalRequest registerRequest2
		 * = o2.registerRequest(ev2);
		 * 
		 * //this employee approves the request, //making the status of the event
		 * "approved" em3.approveRequest(registerRequest1);
		 * 
		 * //the same employee rejects the other request, //making the status of the
		 * event "not-approved" em3.rejectRequest(registerRequest2);
		 * 
		 * 
		 * //An organizer makes a request to delete an event, //and the employee accepts
		 * it ApprovalRequest removalRequest1 = o1.removalRequest(ev6);
		 * em1.approveRequest(removalRequest1);
		 * 
		 * //When an employee deletes an event, there is no need //for approval from
		 * anyone em2.deleteEvent(ev8);
		 * 
		 * // visitor1.searchEvent(12, 7, 2025,
		 * "Thessaloniki International Fairgrounds (TIF HELEXPO)",
		 * "Thessaloniki Tech Fair");
		 * 
		 * System.out.println(EventManager.getInstance().getEvents());
		 * 
		 * 
		 * 
		 * // Egrafi parakolouthisis endilwshs toulaxiston se mia ekdilosi gia olous
		 * tous episkeptes // Set status of two events as "approved".
		 * ev1.setStatus("approved"); ev2.setStatus("approved");
		 * ev4.setStatus("approved"); // Reservation in at least one event for each
		 * Visitor for (Visitor visitor : visitors) { if (ev1.getCurrentCapacity() <
		 * ev1.getMaxCapacity()) { ev1.addReservation(visitor); } else if
		 * (ev2.getCurrentCapacity() < ev2.getMaxCapacity()) {
		 * ev2.addReservation(visitor); } else { System.out.println(visitor.getName() +
		 * " " + visitor.getSurname() + " could not register for any events."); } } //
		 * Print Reservations System.out.println("\nReservations for Event 1:"); for
		 * (Visitor visitor : ev1.getVisitors()) { System.out.println(visitor.getName()
		 * + " " + visitor.getSurname()); }
		 * 
		 * System.out.println("\nReservations for Event 2:"); for (Visitor visitor :
		 * ev2.getVisitors()) { System.out.println(visitor.getName() + " " +
		 * visitor.getSurname()); }
		 */

		/*
		 * Test what will happen in case that one visitor tries to register to an event
		 * which doesn't have any open seats left.
		 */
		/*
		 * System.out.println("\nTrying to make a reservation for visitor3 in Event 4:"
		 * ); ev4.addReservation(visitors.get(2)); // pernei ton 3o episkepti ths listas
		 * 
		 * // Cancel reservation
		 * System.out.println("\nCancelling reservations for Event 4:");
		 * ev4.removeReservation(visitors.get(0)); // Cancel reservation for visitor 1
		 * 
		 * 
		 * // Final reservations
		 * System.out.println("\nFinal Reservations for Event 4:"); for (Visitor visitor
		 * : ev4.getVisitors()) { System.out.println(visitor.getName() + " " +
		 * visitor.getSurname()); }
		 * 
		 */

		Event ev9 = new Event("Thessaloniki Street Art Festival", "Arts",
				"A vibrant street art festival showcasing murals, graffiti, and performances by local and international artists. Visitors can also join workshops and interact with the artists.",
				" Ladadika District", 5, 16, 11, 2024, 10, 30, 8, o1);

		Event ev10 = new Event("Winter Wonderland Market", "Christmas",
				"A magical winter market filled with festive stalls selling local crafts, holiday treats, and seasonal decorations. Includes ice skating, live music, and a visit from Santa Claus.",
				"Aristotelous Square", 4, 15, 12, 2025, 12, 45, 5, o2);

		ApprovalRequest req1 = o1.registerRequest(ev1);
		ApprovalRequest req2 = o1.registerRequest(ev6);
		ApprovalRequest req3 = o2.registerRequest(ev2);
		ApprovalRequest req4 = o2.registerRequest(ev7);
		ApprovalRequest req5 = o3.registerRequest(ev3);
		ApprovalRequest req6 = o3.registerRequest(ev8);
		ApprovalRequest req7 = o4.registerRequest(ev4);
		ApprovalRequest req8 = o4.registerRequest(ev5);

		em1.approveRequest(req1);
		em1.approveRequest(req2);
		em1.approveRequest(req3);
		em1.approveRequest(req4);

		em2.approveRequest(req5);
		em2.approveRequest(req6);

		em3.approveRequest(req7);
		em3.approveRequest(req8);

		ApprovalRequest req9 = o2.registerRequest(ev9);
		ApprovalRequest req10 = o3.registerRequest(ev10);

		em1.approveRequest(req9);
		em2.approveRequest(req10);

		ApprovalRequest req11 = o3.removalRequest(ev1);
		ApprovalRequest req12 = o4.removalRequest(ev4);

		em1.approveRequest(req11);
		// em1.deleteEvent(ev5); //without needing employees approval, the employee just
		// deletes the event

		em3.approveRequest(req12);
		// em3.deleteEvent(ev7); //without needing employees approval, the employee just
		// deletes the event
//--------------------------------------------------------------
		// confirmation that one event is added in the events total list (or in the
		// organizer's list of events)
		// confirmation that one event is deleted in the events total list (or in the
		// organizer's list of events)
//---------------------------------------------------------------
		visitor1.makeReservation(ev1);
		visitor1.makeReservation(ev2);
		visitor1.makeReservation(ev3);

		visitor2.makeReservation(ev4);
		visitor2.makeReservation(ev5);
		visitor2.makeReservation(ev6);

		visitor3.makeReservation(ev7);
		visitor3.makeReservation(ev8);
		visitor3.makeReservation(ev9);
		visitor3.makeReservation(ev10);
		
		visitor4.makeReservation(ev1);
		visitor4.makeReservation(ev2);
		visitor4.makeReservation(ev3);

		visitor5.makeReservation(ev4);
		visitor5.makeReservation(ev5);
		visitor5.makeReservation(ev6);
		visitor5.makeReservation(ev7);

		visitor6.makeReservation(ev8);
		visitor6.makeReservation(ev9);

		visitor7.makeReservation(ev10);
		visitor7.makeReservation(ev1);

		visitor8.makeReservation(ev2);
		visitor8.makeReservation(ev3);

		visitor9.makeReservation(ev4);
		visitor9.makeReservation(ev5);

		visitor10.makeReservation(ev6);
		visitor10.makeReservation(ev7);

		// -----------------------------------------------------------------

		visitor10.makeReservation(ev2);

		// -----------------------------------------------------------------

		LocalDate currentDate = LocalDate.now();

		EventManager eventManager = EventManager.getInstance();

		for (Event event : eventManager.getEvents()) {
			System.out.println(event.getDate() + ", " + event.getStatus());
		}

		// ----------------------------------------------------------------

		System.out.println("Upcoming events: ");
		for (Event event : eventManager.getEvents()) {
			LocalDate eventDate = event.getDate();
			if (eventDate.isAfter(currentDate) && "Approved".equalsIgnoreCase(event.getStatus())) {
				System.out.println(event.getTitle() + ", " + event.getDate() + ", at " + event.getTime());
			}

		}

		// ----------------------------------------------------------------

		System.out.println("Visitors per event: ");

		for (Event event : eventManager.getEvents()) {
			System.out.println("\n\nVisitors for the event \"" + event.getTitle() + "\": ");
			for (Visitor visitor : event.getVisitors()) {
				System.out.println(visitor.getName() + " " + visitor.getSurname() + " (" + visitor.getEmail() + ")");
			}

		}

	}
}
