package com.team1.eventproject.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;

@Service
public class EventServices {

	
	@Autowired
	ApprovalRequestServices approvalRequestServices;

	private ArrayList<Event> allEvents = new ArrayList<>();
	
	
	public EventServices() {
		this.allEvents = new ArrayList<>();
	}

	// method to add an event in the allEvents list, from a specific organizer given
	// his id so as an employee later on (hopefully) approves it by changing its
	// status to either approved (or not-approved)
	public String addEvent(Integer organizerId, String title, String theme, String description,
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
		

		if (isEventDuplicate(event)) {
			return "Event already exists in the events list!";
		} else {
			allEvents.add(event);
			approvalRequestServices.addApprovalRequest("add", organizerId, id, comments);
			return "The registration for the event with id: " + event.getId() + " has been sent for approval.";
		}
	
	}

	
	//HERE THE TWO METHODS INCREASE/DECREASE CURRENT CAPACITY WILL BE ADDED
	 // We need a method to increase currentCapacity of Event
	 	// AND another one to decrease it
	 	// (they will be called by reservationServices when a reservation
	 	// is made or cancelled)

	public String decreaseCurrentCapacity(Integer eventId) {
	    // If EventServices is already injected, no need to create a new instance
	    // EventServices eventServices = new EventServices(); // This line is unnecessary
	    
	    // Find the Event with the given eventId
	    Event event = getEventUsingID(eventId);
	    String message;

	    // Check if the event exists
	    if (event != null) {
	        // Check if there is available capacity before decreasing
	        if (event.getCurrentCapacity() > 0) {
	            // Decrease the capacity of the event by 1
	            event.setCurrentCapacity(event.getCurrentCapacity() - 1);
	            message = "Current capacity of event decreased successfully!";
	        } else {
	            // If the event is already fully booked, do not allow capacity decrease
	            message = "Event is fully booked, cannot decrease capacity.";
	        }
	    } else {
	        // If the event doesn't exist, return an error message
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
	// method to delete an event given its id
	public String deleteEvent(Integer eventId) {
		String message = "Event with id " + eventId + " deleted sucessfully";
		updateEventStatus(eventId, "deleted");
		return message;
	}

	// method to update an event's status
	public String updateEventStatus(Integer eventId, String newEventStatus) {
		String message = "Event with id " + eventId + " updated succesfully!";
		Event event = getEventUsingID(eventId);
		event.setStatus(newEventStatus);
		return message;
	}

	// method to update an event's info

	public String updateEvent(Integer eventId, Integer organizerId, String title, String theme, String description, String location,
	  Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour,
	  Integer minutes, Integer duration, Integer id) {
		
		 Event event = getEventUsingID(eventId);
		 String message;
		 if(event!=null) {
			 
			 if(organizerId!=null) {
				 event.setOrganizerId(organizerId);
			 }
			 
			 if(title!=null) {
				 event.setTitle(title);
			 }
			 
			 if(description!=null) {
				 event.setDescription(description);
			 }
			 
			 if(location!=null) {
				 event.setLocation(location);
			 }
			 
			 if(maxCapacity!=null) {
				 event.setMaxCapacity(maxCapacity);
			 }
			 
			 if(day!=null) {
				 event.setDay(day);
			 }
			 
			 if(month!=null) {
				 event.setMonth(month);
			 }
			 
			 if(year!=null) {
				 event.setYear(year);
			 }
			 
			 if(hour!=null) {
				 event.setHour(hour);
			 }
			 
			 if(minutes!=null) {
				 event.setMinutes(minutes);
			 }
			 
			 if(duration!=null) {
				 event.setDuration(duration);
			 }
			 
			 message="Event has been updated";
			 return message;
		 }else {
			 message="Event not found!";
			 return message;
		 }
	 }

	public ArrayList<Event> getEventsForOrganizer(Integer organizerId) {
		ArrayList<Event> organizerEvents = new ArrayList<>();

		for (Event event : allEvents) {
			if (organizerId.equals(event.getOrganizerId())) {
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
	            if (isValidDate(event.getYear(), event.getMonth(), event.getDay())) {
	                LocalDate date = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
	                if (currentDate.isBefore(date) && organizerId.equals(event.getOrganizerId())) {
	                    upcomingEvents.add(event);
	                }
	            }
	        } catch (Exception e) {
	            System.err.println(
	                    "Error trying to retrieve upcoming events (date or organizer not valid): " + e.getMessage());
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
	/*
	 * public void getReservationsForOrganizersEvents() {
	 * 
	 * }
	 */
	// searchEvents is the same as getting a certain event-->getEvent
	public List<Event> searchEvents(Integer id, Integer day, Integer month, Integer year, String location,
			String theme) {
		return getAllEvents().stream().filter(event -> id.equals(null) || id.equals(event.getId())).filter(event -> {
			if (day != null && month != null && year != null) {
				LocalDate givenDate = LocalDate.of(year, month, day);
				LocalDate eventDate = LocalDate.of(event.getYear(), event.getMonth(), event.getDay());
				return givenDate.isEqual(eventDate);
			}
			return true;
		}).filter(event -> location.equals(null) || location.equals(event.getLocation()))
				.filter(event -> theme.equals(null) || theme.equals(event.getTheme())).collect(Collectors.toList());
	}

	// να βλεπω αναφορες με την κατασταση των εκδηλωσεων 1ο απο το doc

	// method that finds a specific event given an id as a parameter
	public Event getEventUsingID(Integer eventId) {
		for (Event event : allEvents) {
			if (eventId.equals(event.getId())) {
				return event;
			}
		}
		return null;
	}

	// method to return all the events from the list
	public ArrayList<Event> getAllEvents() {
		return allEvents;
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

	
	/*
	 * Checks if the event has already been registered. Since the ID is given
	 * automatically, another way of checking for duplicates had to be implemented.
	 * 
	 * If the same organizer has made an event with the same Title at the same
	 * date and time, then the isEventDuplicate is true
	 */
	private Boolean isEventDuplicate(Event event) {
	    for (Event ev : allEvents) {
	        if (ev.getOrganizerId().equals(event.getOrganizerId()) &&
	            ev.getTitle().equals(event.getTitle()) &&
	            ev.getDay().equals(event.getDay()) &&
	            ev.getMonth().equals(event.getMonth()) &&
	            ev.getYear().equals(event.getYear()) &&
	            ev.getHour().equals(event.getHour()) &&
	            ev.getMinutes().equals(event.getMinutes())) 
	           
	        	return true;	        
	    }
	    return false;

	}

	private Boolean isValidDate(Integer year, Integer month, Integer day) {
		try {
			LocalDate.of(year, month, day);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}

 
	
