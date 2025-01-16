package com.team1.eventproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;
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

    // Create a reservation for an event and a visitor
    public String createReservation(Integer eventId, Integer visitorId) {
        Event event = eventServices.getEventUsingID(eventId);
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);

        if (event != null && visitor != null) {
            // Create the reservation using ReservationServices
            reservationServices.addReservation(eventId, visitorId);
            
            // Update the event's capacity through EventServices
            eventServices.decreaseCurrentCapacity(eventId);
            
            return "Reservation created successfully!";
        } else {
            return "Event or Visitor not found!";
        }
    }

    // Cancel a reservation for an event and a visitor
    public String cancelReservation(Integer eventId, Integer visitorId) {
        Event event = eventServices.getEventUsingID(eventId);
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);

        if (event != null && visitor != null) {
            // Cancel the reservation using ReservationServices
            reservationServices.cancelReservation(eventId, visitorId);
            
            // Update the event's capacity through EventServices
            eventServices.increaseCurrentCapacity(eventId);
            
            return "Reservation cancelled successfully!";
        } else {
            return "Event or Visitor not found!";
        }
    }

    
    public void cancelAllReservationsForVisitor(Integer visitorId) {
    	// PREPEI NA FTIAXW AUTH THN METHODO
    }
    // Get the total number of reservations for a specific event
    public int getReservationsCountForEvent(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event != null) {
            return reservationServices.getReservationsByEvent(eventId).size();
        } else {
            return 0;
        }
    }

    // Check if an event is fully booked
    public boolean isEventFullyBooked(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event != null) {
            return event.getCurrentCapacity() <= 0; // If the capacity is 0 or less, the event is fully booked
        } else {
            return false;
        }
    }
}
