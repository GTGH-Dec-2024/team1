package com.team1.eventproject.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service
public class EventServices {

	@Autowired
	ApprovalRequestServices approvalRequestServices;
	@Autowired
	ReservationServices reservationServices;

	private ArrayList<Event> allEvents = new ArrayList<>();
	private ArrayList<Event> deletedEvents = new ArrayList<>();

	public EventServices() {
		this.allEvents = allEvents;
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
	public void deleteEvent(Integer eventId) {
		Event event = getEventUsingID(eventId);
		event.setStatus("deleted");
	}

	// method to update an event's status
	public void updateEvent(Integer eventId, String newEventStatus) {
		Event event = getEventUsingID(eventId);
		event.setStatus(newEventStatus);
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
			LocalDate date = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
			if (currentDate.equals(date) && organizerId == event.getOrganizerId()) {
				upcomingEvents.add(event);
			}
		}

		return upcomingEvents;
	}

	public void visitorsPerEvent() {

		for (Event event : allEvents) {
			System.out.println("Event: " + event.getTitle());
			List<Reservation> reservationsForThisEvent = reservationServices.getReservationsByEvent(event.getId());
			if (reservationsForThisEvent.isEmpty()) {
				System.out.println("No visitors yet!");
			} else {
				for (Reservation reservation : reservationsForThisEvent) {
					System.out.println(reservation.getVisitor().getName() + " " + reservation.getVisitor().getSurname()
							+ " (" + reservation.getVisitor().getId() + ")");
				}
			}
		}
	}

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
		if(event != null) {
			event.setCurrentCapacity(event.getCurrentCapacity()-1);
			message="Current capacity of event decreased succesfully!";
		}else {
			message="Event not found!";
		}
		return message;
	}

	public String increaseCurrentCapacity(Integer eventId) {
		Event event = getEventUsingID(eventId);
		String message;
		if(event != null && event.getCurrentCapacity()>0) {
			event.setCurrentCapacity(event.getCurrentCapacity()+1);
			message="Current capacity of event decreased succesfully!";
		}else {
			message="Event not found or the capacity if full!";
		}
		return message;
	}
}
