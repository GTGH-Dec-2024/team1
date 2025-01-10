package com.team1.eventproject.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;

@Service
public class EventServices {

	@Autowired
	ApprovalRequestServices approvalRequestServices;

	private ArrayList<Event> allEvents = new ArrayList<>();

	public EventServices() {
		this.allEvents = allEvents;
	}

	// method to add an event in the allEvents list, from a specific organizer given
	// his id so as an employee later on (hopefully) approves it by changing its
	// status to either approved (or not-approved)
	public void addEvent(int eventId, int organizerId, String title, String theme, String description, String location,
			int maxCapacity, int day, int month, int year, int hour, int minutes, int duration) {

		Event event = new Event(title, theme, description, location, maxCapacity, day, month, year, hour, minutes,
				duration);

		if (allEvents.contains(event.getId())) {
			System.out.println("Event already exists in the events list!");
		} else {
			allEvents.add(event);
			approvalRequestServices.addApprovalRequest(event, "register");
		}

	}

	
	
	  public void deleteEvent(int eventId, int employeeId) {
		  
	  }
	 
	  
	 
	// 1st method to search the events, from the whole events list, given attributes
	// like date, location and theme
	public ArrayList<Event> searchEvents(int day, int month, int year, String location, String theme) {
		ArrayList<Event> events = new ArrayList<>();

		LocalDate givenDate = LocalDate.of(year, month, day);

		for (Event event : allEvents) {
			LocalDate eventDate = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
			if (givenDate.isEqual(eventDate) && location.equals(event.getLocation())
					&& theme.equals(event.getTheme())) {
				events.add(event);
			}
		}

		return events;
	}

	// 2nd method to search the events, from the whole events list, given only the
	// theme attribute
	public ArrayList<Event> searchEventsByTheme(String theme) {

		ArrayList<Event> events = new ArrayList<>();
		for (Event event : allEvents) {
			if (theme.equals(event.getTheme())) {
				events.add(event);
			}
		}

		return events;
	}

	// 3rd method to search the events, from the whole events list, given only the
	// location attribute
	public ArrayList<Event> searchEventsByLocation(String location) {

		ArrayList<Event> events = new ArrayList<>();
		for (Event event : allEvents) {
			if (location.equals(event.getLocation())) {
				events.add(event);
			}
		}

		return events;
	}

	// 4rth method to search the events, from the whole events list, given only the
	// date attribute
	public ArrayList<Event> searchEventsByDate(int day, int month, int year) {

		ArrayList<Event> events = new ArrayList<>();
		LocalDate givenDate = LocalDate.of(year, month, day);

		for (Event event : allEvents) {
			LocalDate eventDate = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
			if (givenDate.isEqual(eventDate)) {
				events.add(event);
			}
		}

		return events;
	}

	// method that finds a specific event given an id as a parameter
	public Event getEventUsingID(int eventId) {
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

}
