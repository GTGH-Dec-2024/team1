package com.team1.eventproject.services;

import java.util.ArrayList;

/*import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;*/

/*
 * public String exportReservationsToPdf(String filepath) { String message;
 * 
 * try{
 * 
 * PdfWriter writer = new PdfWriter(filepath); PdfDocument pdfDocument = new
 * PdfDocument(writer); Document document = new Document(pdfDocument);
 * 
 * document.add(new Paragraph("Event Reservations").setBold().setFontSize(16));
 * 
 * float[] columnWidths= {1,3,3}; Table table = new Table(columnWidths);
 * 
 * table.addHeaderCell("RESERVATION ID"); table.addHeaderCell("VISITOR NAME");
 * table.addHeaderCell("EVENT TITLE");
 * 
 * for(Reservation reservation : reservations) {
 * table.addCell(String.valueOf(reservation.getId()));
 * table.addCell(String.valueOf(reservation.getVisitor().getName()));
 * table.addCell(String.valueOf(reservation.getEvent().getTitle())); }
 * 
 * document.add(table); document.close();
 * 
 * message="Reservations for each event exported succesfully to "+filepath;
 * }catch(Exception e) { e.printStackTrace(); message =
 * "Error exporting reservations to PDF: "+e.getMessage(); }
 * 
 * return message; }
 */



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service
public class ReservationServices {

    // List that stores all reservations
    private List<Reservation> reservations;

    @Autowired
    private ReservationManagerServices reservationManagerServices; // Dependency to ReservationManagerServices

    // Constructor to initialize the reservations list
    public ReservationServices() {
        this.reservations = new ArrayList<>();
    }

    /*
     * Adds a new reservation for a visitor to an event.
     * @param visitorId The ID of the visitor.
     * @param eventId The ID of the event.
     * @return A message indicating the result of the operation.
     */
    public String addReservation(Integer visitorId, Integer eventId) {
        // Check if the visitor exists
        Visitor visitor = reservationManagerServices.getVisitorUsingID(visitorId);
        if (visitor == null) {
            return "Visitor with ID " + visitorId + " not found.";
        }

        // Check if the event exists
        Event event = reservationManagerServices.getEventUsingID(eventId);
        if (event == null) {
            return "Event with ID " + eventId + " not found.";
        }

        // Check if a reservation already exists for this visitor and event
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId().equals(visitorId) && reservation.getEventId().equals(eventId)) {
                return "This reservation already exists.";
            }
        }

        // Check if the event has available capacity
        if (event.getCurrentCapacity() <= 0) {
            return "Event with ID " + eventId + " is fully booked.";
        }

        // Generate a new ID for the reservation
        Integer id = reservations.isEmpty() ? 1 : reservations.get(reservations.size() - 1).getId() + 1;

        // Create and add the new reservation
        Reservation newReservation = new Reservation(visitorId, eventId, id);
        reservations.add(newReservation);

        // Update the event's capacity
        String capacityUpdateMessage = reservationManagerServices.decreaseEventCapacity(eventId);
        if (!capacityUpdateMessage.contains("successfully")) {
            // Remove the reservation if capacity update fails
            reservations.remove(newReservation);
            return capacityUpdateMessage;
        }

        return "Reservation made successfully for event: " + event.getTitle();
    }

    /*
     * Cancels a reservation based on the visitor ID and event ID.
     * @param visitorId The ID of the visitor.
     * @param eventId The ID of the event.
     * @return A message indicating the result of the cancellation.
     */
    public String cancelReservation(Integer visitorId, Integer eventId) {
        // Find the reservation based on visitor ID and event ID
        Reservation reservationToCancel = null;
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId().equals(visitorId) && reservation.getEventId().equals(eventId)) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel == null) {
            return "Reservation not found for visitor ID " + visitorId + " and event ID " + eventId + ".";
        }

        // Find the event associated with the reservation
        Event event = reservationManagerServices.getEventUsingID(eventId);
        if (event == null) {
            return "Event with ID " + eventId + " not found.";
        }

        // Remove the reservation
        reservations.remove(reservationToCancel);

        // Increase the event's capacity
        String capacityUpdateMessage = reservationManagerServices.increaseEventCapacity(eventId);
        return "Reservation cancelled successfully for visitor: " + visitorId + " and event: " + event.getTitle() + ". " + capacityUpdateMessage;
    }

    /*
     * Updates an existing reservation with new visitor and event IDs.
     * @param reservationId The ID of the reservation to be updated.
     * @param newVisitorId The new visitor ID.
     * @param newEventId The new event ID.
     * @return A message indicating the result of the update.
     */
    public String updateReservation(Integer reservationId, Integer newVisitorId, Integer newEventId) {
        // Find the reservation by its ID
        Reservation reservationToUpdate = getReservationUsingID(reservationId);

        if (reservationToUpdate == null) {
            return "Reservation with ID " + reservationId + " not found.";
        }

        // Check if the new visitor exists
        if (reservationManagerServices.getVisitorUsingID(newVisitorId) == null) {
            return "Visitor with ID " + newVisitorId + " not found.";
        }

        // Check if the new event exists
        if (reservationManagerServices.getEventUsingID(newEventId) == null) {
            return "Event with ID " + newEventId + " not found.";
        }

        // Update the reservation with new visitor and event IDs
        reservationToUpdate.setVisitorId(newVisitorId);
        reservationToUpdate.setEventId(newEventId);

        return "Reservation with ID " + reservationId + " updated successfully.";
    }

    /*
     * Returns all reservations.
     * Return A list of all reservations.
     */
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }

    /*
     * Retrieves reservations for a specific visitor.
     * @param visitorId The ID of the visitor.
     * @return A list of reservations for the specified visitor.
     */
    public List<Reservation> getReservationsByVisitor(Integer visitorId) {
        List<Reservation> visitorReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId().equals(visitorId)) {
                visitorReservations.add(reservation);
            }
        }
        return visitorReservations;
    }

    /*
     * Retrieves reservations for a specific event.
     * @param eventId The ID of the event.
     * @return A list of reservations for the specified event.
     */
    public List<Reservation> getReservationsByEvent(Integer eventId) {
        List<Reservation> eventReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(eventId)) {
                eventReservations.add(reservation);
            }
        }
        return eventReservations;
    }

    /*
     * Counts the number of reservations for a specific event.
     * @param eventId The ID of the event.
     * @return The number of reservations for the event.
     */
    public int countReservationsForEvent(Integer eventId) {
        int count = 0;
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(eventId)) {
                count++;
            }
        }
        return count;
    }

    /*
     * Finds a reservation by its ID.
     * @param id The ID of the reservation.
     * @return The reservation object or null if not found.
     */
    public Reservation getReservationUsingID(Integer id) {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(id)) {
                return reservation;
            }
        }
        return null;
    }

    /*
     * Cancels all reservations for a specific event.
     * @param eventID The ID of the event.
     * @return A message indicating the result of the operation.
     */
    public String cancelAllReservationsForEvent(Integer eventID) {
        List<Reservation> tempReservations = getReservationsByEvent(eventID);
        if (tempReservations.isEmpty()) {
            return "This event doesn't have any reservations.";
        }

        for (Reservation reservation : tempReservations) {
            cancelReservation(reservation.getId());
        }

        return "All reservations for this event have been cancelled.";
    }
}


