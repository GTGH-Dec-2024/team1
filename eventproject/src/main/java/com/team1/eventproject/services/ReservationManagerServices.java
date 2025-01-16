package com.team1.eventproject.services;

//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Event;
//import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service
public class ReservationManagerServices {

	@Autowired
	private EventServices eventServices;
	
	@Autowired
	private ReservationServices reservationServices;
	
	@Autowired
	private VisitorServices visitorServices;

	/*
	 * This method returns the titles of all the events that have been added, together with the visitors' list for each event. 
	 * This is done by using a "visitorsperevent" String and updating it each time. 
	 */
	public String visitorsPerEvent() {
		String visitorsperevent = "";

		for (Event event : eventServices.getAllEvents()) {
			visitorsperevent += "--Event: " + event.getTitle() + "--\n";
			// Get all the reservations for the event
			List<Reservation> reservationsForThisEvent = reservationServices.getReservationsByEvent(event.getId());

			// if there are no reservations, inform the user
			if (reservationsForThisEvent.isEmpty()) {
				visitorsperevent += "No visitors yet!\n";
			} else {
				//for each reservation, find the visitors using their ID
				for (Reservation reservation : reservationsForThisEvent) {
					Visitor visitor = visitorServices.getVisitorUsingID(reservation.getVisitorId());

					if (visitor != null) {
						visitorsperevent += visitor.getName() + " " + visitor.getSurname() + " (id = " + visitor.getId()
								+ ")\n";
					}
				}
			}
		}
		return visitorsperevent;
	}

	/*
	 * Based on the ID of an organizer, this method returns a list of reservations for all the events they have organized
	 * 
	 */
	public List<Reservation> getReservationsForOrganizersEvents(Integer organizerId) {
		// Get the events of the organizer using EventServices
		List<Event> organizerEvents = eventServices.getEventsForOrganizer(organizerId);

		// Create a list to store reservations for the organizer's events
		List<Reservation> reservationsForOrganizerEvents = new ArrayList<>();

		// Loop through each event to get the reservations
		for (Event event : organizerEvents) {
			// Get all the reservations for the current event
			reservationsForOrganizerEvents.addAll(reservationServices.getReservationsByEvent(event.getId()));
		}
		return reservationsForOrganizerEvents;

	}

}