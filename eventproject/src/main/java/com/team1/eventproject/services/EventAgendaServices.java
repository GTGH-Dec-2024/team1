package com.team1.eventproject.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Reservation;

@Service
public class EventAgendaServices {
	
	@Autowired
	private EventServices eventServices;
	@Autowired
	private OrganizerServices organizerServices;
	@Autowired
	private ApprovalRequestServices apprivalRequestServices;
	@Autowired
	private ReservationServices reservationServices;
	

	public String writeEventAgendaToFile() {
		String message;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("event-agenda.txt"))) {

			writer.write("********UPCOMING EVENTS FOR EACH ORGANIZER*********");
			ArrayList<Organizer> allOrganizers = organizerServices.getAllOrganizers();
			for (Organizer organizer : allOrganizers) {
				writer.write("Organizer: " + organizer.getName() + " " + organizer.getSurname() + " ("
						+ organizer.getId() + ")");
				List<Event> upcomingEvents = organizerServices.getUpcomingEventsPerOrganizer(organizer.getId());
				if (upcomingEvents.isEmpty()) {
					writer.write("No upcoming events!\n");
				} else {
					for (Event event : upcomingEvents) {
						writer.write("-" + event.toString() + "\n");
					}
				}
			}

			writer.write("********RESERVATIONS FOR EACH EVENT*********");
			ArrayList<Event> allEvents = eventServices.getAllEvents();
			for (Event event : allEvents) {
				writer.write("Event: " + event.getTitle() + " (" + event.getId() + ")");
				List<Reservation> reservations = reservationServices.getReservationsByEvent(event.getId());
				if (reservations.isEmpty()) {
					writer.write("No reservations for that event!\n");
				} else {
					for (Reservation reservation : reservations) {
						writer.write("-" + reservation.toString() + "\n");
					}
				}
			}

			writer.write("********ALL PENDING REQUESTS*********");
			List<ApprovalRequest> pendingRequests = approvalRequestServices.getPendingRequests();

			if (pendingRequests.isEmpty()) {
				writer.write("No pending requests!");
			} else {
				for (ApprovalRequest request : pendingRequests) {
					writer.write("Request: " + request.toString() + "\n");
				}
			}

			message = "The event agenda was succesfully written to event-agenda.txt file";
		} catch (IOException e) {
			message = "Error while writing the file: " + e.getMessage();
		}

		return message;
	}
	
}
