package com.team1.eventproject.services;
/* H klasi Reservation Services tha xrisimopoieitai san pinakas-vasi dedomenwn opou tha apothikeuontai 
 * oles oi kratiseis pou exoun kanei oi visitors gia ta events pou theloun na parakolouthisoun.
 */
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.entities.Event;

import java.util.ArrayList;
import java.util.List;
public class ReservationServices {
	private List<Reservation> reservations; // Lista pou apothikeuei oles tis kratiseiw
    // Constructor
    public ReservationService() {
        this.reservations = new ArrayList<>();
    }

    // Prosthiki Kratisis
    public String addReservation(Visitor visitor, Event event) {
        // Checks if there is already reservation for the specifi visitor and the specific event.
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
                return "This reservation already exists.";
            }
        }
        // make a new reservation and add it to the list
        Reservation newReservation = new Reservation(visitor, event);
        reservations.add(newReservation);
        return "Reservation made succesfully for event: " + event.getName();
    }

    // Cancel Reservation
    public String cancelReservation(Visitor visitor, Event event) {
        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
                reservationToCancel = reservation;
                break;
            }
        }
        if (reservationToCancel != null) {
            reservations.remove(reservationToCancel);
            return "Reservation cancelled for event: " + event.getName();
        }
        return "Reservation not found in order to be canceled.";
    }

    // Return all the reservations
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    // Return reservations for the specific Visitor
    public List<Reservation> getReservationsByVisitor(Visitor visitor) {
        List<Reservation> visitorReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor)) {
                visitorReservations.add(reservation);
            }
        }
        return visitorReservations;
    }

    // Return reservations for the specific event.
    public List<Reservation> getReservationsByEvent(Event event) {
        List<Reservation> eventReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                eventReservations.add(reservation);
            }
        }
        return eventReservations;
    }

    // count reservations for the event
    public int countReservationsForEvent(Event event) {
        int count = 0;
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                count++;
            }
        }
        return count;
    }
	
	
		

}
