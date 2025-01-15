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
import com.team1.eventproject.entities.Visitor;

@Service
public class ReservationManagerServices {

    @Autowired
    private EventServices EventServices;

    @Autowired
    private EventServices ApprovalRequestServices;
    @Autowired
    private ReservationServices ReservationServices;

    @Autowired
    private VisitorServices VisitorServices;

    //EDW THA MPEI H METHODOS VISITORSPEREVENT
    public void visitorsPerEvent() {
        // Loop through all events
        for (Event event : EventServices.getAllEvents()) {
            System.out.println("Event: " + event.getTitle());
            
            // Get the reservations for the current event using the eventId
            List<Reservation> reservationsForThisEvent = ReservationServices.getReservationsByEvent(event.getId());
            
            if (reservationsForThisEvent.isEmpty()) {
                System.out.println("No visitors yet!");
            } else {
                // Loop through each reservation and get visitor details
                for (Reservation reservation : reservationsForThisEvent) {
                    // Fetch the visitor by the visitorId from VisitorServices
                    Visitor visitor = VisitorServices.getVisitorUsingID(reservation.getVisitorId());
                    
                    if (visitor != null) {
                        // Print the visitor's details
                        System.out.println(visitor.getName() + " " + visitor.getSurname() + " (" + visitor.getId() + ")");
                    } else {
                        System.out.println("Visitor with ID " + reservation.getVisitorId() + " not found.");
                    }
                }
            }
        }
    }

    
 // Method to delete a visitor
    public boolean deleteVisitor(Integer visitorId) {
        // Retrieve the visitor by ID
        Visitor visitor = VisitorServices.getVisitorUsingID(visitorId);

        // If the visitor is not found or is already deleted
        if (visitor == null || visitor.getStatus().equalsIgnoreCase("deleted")) {
            return false; // Visitor not found or already deleted
        }

        // Mark the visitor as deleted
        visitor.setStatus("deleted");

        // Call ReservationServices to cancel all related reservations for this visitor
        ReservationServices.cancelAllReservationsForVisitor(visitorId);
        //SOS AUTO EDW DEN DHMIOURGEI KUKLO?

        return true; // Successful deletion
    }

	
		public void getReservationsForOrganizersEvents(Integer organizerId) {
		    // Get the events of the organizer using EventServices
		    List<Event> organizerEvents = EventServices.getEventsForOrganizer(organizerId);

		    // Create a list to store reservations for the organizer's events
		    List<Reservation> reservationsForOrganizerEvents = new ArrayList<>();

		    // Loop through each event to get the reservations
		    for (Event event : organizerEvents) {
		        // Get the reservations for the current event
		        List<Reservation> eventReservations = ReservationServices.getReservationsByEvent(event.getId());

		        // Add the reservations of the event to the list
		        reservationsForOrganizerEvents.addAll(eventReservations);
		    }
	}
	
	
	
		public String writeEventAgendaToFile() {
			String message;
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("event-agenda.txt"))) {

				writer.write("********UPCOMING EVENTS FOR EACH ORGANIZER*********");
				List<Organizer> allOrganizers = organizerServices.getAllOrganizers();
				for (Organizer organizer : allOrganizers) {
					writer.write("Organizer: " + organizer.getName() + " " + organizer.getSurname() + " ("
							+ organizer.getId() + ")");
					List<Event> upcomingEvents = EventServices.getUpcomingEventsPerOrganizer(organizer.getId());
					if (upcomingEvents.isEmpty()) {
						writer.write("No upcoming events!\n");
					} else {
						for (Event event : upcomingEvents) {
							writer.write("-" + event.toString() + "\n");
						}
					}
				}

				writer.write("********RESERVATIONS FOR EACH EVENT*********");
				List<Event> allEvents = EventServices.getAllEvents();
				for (Event event : allEvents) {
					writer.write("Event: " + event.getTitle() + " (" + event.getId() + ")");
					List<Reservation> reservations = ReservationServices.getReservationsByEvent(event.getId());
					if (reservations.isEmpty()) {
						writer.write("No reservations for that event!\n");
					} else {
						for (Reservation reservation : reservations) {
							writer.write("-" + reservation.toString() + "\n");
						}
					}
				}

				writer.write("********ALL PENDING REQUESTS*********");
				List<ApprovalRequest> pendingRequests = ApprovalRequestServices.getPendingRequests();
				
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