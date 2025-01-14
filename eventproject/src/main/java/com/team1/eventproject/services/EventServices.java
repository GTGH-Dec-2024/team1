package com.team1.eventproject.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Reservation;

@Service
public class EventServices {

	@Autowired
	@Lazy
	ApprovalRequestServices approvalRequestServices;
	@Autowired
	@Lazy
	ReservationServices reservationServices;
	@Autowired
	@Lazy
	EventServices eventServices;
	@Autowired
	@Lazy
	OrganizerServices organizerServices;

	private ArrayList<Event> allEvents = new ArrayList<>();
	private ArrayList<Event> deletedEvents = new ArrayList<>();

	public EventServices() {
		this.allEvents = new ArrayList<>();
	}

	// method to add an event in the allEvents list, from a specific organizer given
	// his id so as an employee later on (hopefully) approves it by changing its
	// status to either approved (or not-approved)
	public String addEvent(Integer eventId, Integer organizerId, String title, String theme, String description,
			String location, Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour,
			Integer minutes, Integer duration, String comments) {

		/*
		 * We want all IDs to be given automatically. Therefore, we use the allEmployees
		 * list to help us. If the list is empty, then we know it is the first object
		 * that will be made so its id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added, and by
		 * increasing it by 1 we get the new id!
		 * 
		 */
		int id;
		if (allEvents.isEmpty()) {
			id = 1;

		} else {
			id = allEvents.get(allEvents.size() - 1).getId() + 1;
		}

		Event event = new Event(organizerId, title, theme, description, location, maxCapacity, day, month, year, hour,
				minutes, duration, id);

		String message;

		if (allEvents.contains(event.getId())) {
			message = "Event already exists in the events list!";
		} else {
			allEvents.add(event);
			approvalRequestServices.addApprovalRequest("delete", organizerId, eventId, comments);
			message = "The registration for the event " + event.getId() + " have been sent for approval.";
		}
		return message;
	}

	// method to delete an event given its id
	public String deleteEvent(Integer eventId) {
		String message = "Event with id " + eventId + " deleted sucessfully";
		Event event = getEventUsingID(eventId);
		event.setStatus("deleted");
		return message;
	}

	// method to update an event's status
	public String updateEvent(Integer eventId, String newEventStatus) {
		String message = "Event with id " + eventId + " updated succesfully!";
		Event event = getEventUsingID(eventId);
		event.setStatus(newEventStatus);
		return message;
	}

	public ArrayList<Event> getEventsForOrganizer(Integer organizerId) {
		ArrayList<Event> organizerEvents = new ArrayList<>();

		for (Event event : allEvents) {
			if (organizerId == event.getOrganizerId()) {
				organizerEvents.add(event);
			}
		}

		return organizerEvents;
	}

	public ArrayList<Event> getUpcomingEventsPerOrganizer(Integer organizerId) {
		ArrayList<Event> upcomingEvents = new ArrayList<>();

		LocalDate currentDate = LocalDate.now();

		for (Event event : allEvents) {
			try {
				if (isValidDate(event.getYear(), event.getMonth(), event.getDay()) && isValidOrganizer(organizerId)) {
					LocalDate date = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
					if (currentDate.isBefore(date) && organizerId == event.getOrganizerId()) {
						upcomingEvents.add(event);
					}
				}
			} catch (Exception e) {
				System.err.println("Error trying to retrieve upcomming events(date or organizer not valid): "+e.getMessage());
			}

		}

		return upcomingEvents;
	}

	/*
	 * public void visitorsPerEvent() {
	 * 
	 * for (Event event : allEvents) { System.out.println("Event: " +
	 * event.getTitle()); List<Reservation> reservationsForThisEvent =
	 * reservationServices.getReservationsByEvent(event.getId()); if
	 * (reservationsForThisEvent.isEmpty()) {
	 * System.out.println("No visitors yet!"); } else { for (Reservation reservation
	 * : reservationsForThisEvent) {
	 * System.out.println(reservation.getVisitor().getName() + " " +
	 * reservation.getVisitor().getSurname() + " (" +
	 * reservation.getVisitor().getId() + ")"); } } } }
	 */

	public void getReservationsForOrganizersEvents() {

	}

	// searchEvents is the same as getting a certain event-->getEvent
	public List<Event> searchEvents(Integer id, Integer day, Integer month, Integer year, String location,
			String theme) {
		return getAllEvents().stream().filter(event -> id == null || id.equals(event.getId())).filter(event -> {
			if (day != null && month != null && year != null) {
				LocalDate givenDate = LocalDate.of(year, month, day);
				LocalDate eventDate = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
				return givenDate.isEqual(eventDate);
			}
			return true;
		}).filter(event -> location == null || location.equals(event.getLocation()))
				.filter(event -> theme == null || theme.equals(event.getTheme())).collect(Collectors.toList());
	}

	// να βλεπω αναφορες με την κατασταση των εκδηλωσεων 1ο απο το doc

	// method that finds a specific event given an id as a parameter
	public Event getEventUsingID(Integer eventId) {
		for (Event event : allEvents) {
			if (eventId == event.getId()) {
				return event;
			}
		}
		return null;
	}

	// method to return all the events from the list
	public ArrayList<Event> getAllEvents() {
		return allEvents;
	}

	// We need a method to increase currentCapacity of Event
	// AND another one to decrease it
	// (they will be called by reservationServices when a reservation
	// is made or cancelled)

	public String decreaseCurrentCapacity(Integer eventId) {
		Event event = getEventUsingID(eventId);
		String message;
		if (event != null) {
			event.setCurrentCapacity(event.getCurrentCapacity() - 1);
			message = "Current capacity of event decreased succesfully!";
		} else {
			message = "Event not found!";
		}
		return message;
	}

	public String increaseCurrentCapacity(Integer eventId) {
		Event event = getEventUsingID(eventId);
		String message;
		if (event != null && event.getCurrentCapacity() > 0) {
			event.setCurrentCapacity(event.getCurrentCapacity() + 1);
			message = "Current capacity of event decreased succesfully!";
		} else {
			message = "Event not found or the capacity if full!";
		}
		return message;
	}

	

	/*
	 * This method takes the ID of an organizer, and utilizes the
	 * getUpcomingEventsPerOrganizer method to find and delete all their planned
	 * events.
	 * 
	 */
	public String cancelAllEventsForOrganizer(Integer organizerID) {
		List<Event> tempEvents = getUpcomingEventsPerOrganizer(organizerID);

		if (tempEvents.isEmpty()) {
			return "This organizer doesn't have any upcoming events.";
		}

		for (Event event : tempEvents) {
			deleteEvent(event.getId());
		}

		return "All events for this organizer have been cancelled.";
	}

	/*
	 * Utilizes the title of an event (String) to return the event ID
	 *
	 */
	public Integer getEventIDFromTitle(String title) {
		for (Event event : allEvents) {
			if (title.equalsIgnoreCase(event.getTitle())) {
				return event.getId();
			}

		}
		// all event IDs are bigger than 0, so if an Event is not
		// found, 0 is returned.
		return 0;
	}

	private Boolean isValidDate(Integer year, Integer month, Integer day) {
		try {
			LocalDate.of(year, month, day);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private Boolean isValidOrganizer(Integer organizerId) {
		boolean isValid = false;
		for (Organizer organizer : organizerServices.getAllOrganizers()) {
			if (organizerId.equals(organizer.getId())) {
				isValid = true;
			} else {
				isValid = false;
			}
		}
		return isValid;
	}
}
