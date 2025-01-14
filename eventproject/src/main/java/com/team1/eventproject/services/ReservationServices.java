package com.team1.eventproject.services;

/*import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;*/

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service 
public class ReservationServices {

    private List<Reservation> reservations; // Lista pou apothikeuei oles tis kratiseis

    @Autowired
    private EventServices eventServices; // Exartisi gia ta event services

    @Autowired
    private VisitorServices visitorServices; // Exartisi gia ta visitor services

    
    // Constructor gia tin arxikopoiisi tis listas
    public ReservationServices() {
        this.reservations = new ArrayList<>();
    }

    
    public String addReservation(int visitorId, Integer eventId) {
        // Find the Visitor and Event using their IDs
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        Event event = eventServices.getEventUsingID(eventId);

        // Check if the Visitor exists
        if (visitor == null) {
            return "Visitor with ID " + visitorId + " not found.";
        }

        // Check if the Event exists
        if (event == null) {
            return "Event with ID " + eventId + " not found.";
        }

        // Check if a reservation already exists for the same visitor and event
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId() == visitorId && reservation.getEventId() == eventId) {
                return "This reservation already exists.";
            }
        }

        // Check if the event has available capacity
        if (event.getCurrentCapacity() <= 0) {
            return "Event with ID " + eventId + " is fully booked.";
        }

        // Generate a new ID for the reservation
        Integer id;  
        if (reservations.isEmpty()) {
            id = 1;
        } else {
            id = reservations.get(reservations.size() - 1).getId() + 1;
        }

        // Create a new reservation for the visitor and add it to the list
        Reservation newReservation = new Reservation(visitorId, eventId, id);
        reservations.add(newReservation);

        // Call the decreaseCurrentCapacity method from EventServices
        String capacityUpdateMessage = eventServices.decreaseCurrentCapacity(eventId);
        if (!capacityUpdateMessage.contains("successfully")) {
            // If capacity decrease fails, remove the reservation to maintain consistency
            reservations.remove(newReservation);
            return capacityUpdateMessage;
        }

        return "Reservation made successfully for event: " + event.getTitle();
    }

 // Method which cancels a reservation given the reservation id
    public String cancelReservation(Integer reservationId) {
        // Find the reservation using its ID
        Reservation reservationToCancel = getReservationUsingID(reservationId);

        // Check if the reservation exists
        if (reservationToCancel == null) {
            return "Reservation with ID " + reservationId + " not found.";
        }

        // Get the event of the reservation to update its capacity
        Event event = eventServices.getEventUsingID(reservationToCancel.getEventId());

        // Check if the event exists
        if (event == null) {
            return "Event with ID " + reservationToCancel.getEventId() + " not found.";
        }

        // Remove the reservation from the list
        reservations.remove(reservationToCancel);

        // Call increaseCurrentCapacity from EventServices
        String capacityUpdateMessage = eventServices.increaseCurrentCapacity(event.getId());

        // Return the final message
        return "Reservation with ID " + reservationId + " cancelled successfully for event: " 
                + event.getTitle() + ". " + capacityUpdateMessage;
    }

    public String updateReservation(Integer reservationId, Integer newVisitorId, Integer newEventId) {
        // Find the reservation based on the provided ID
        Reservation reservationToUpdate = getReservationUsingID(reservationId);

        // If the reservation doesn't exist, return silently without an error
        if (reservationToUpdate == null) {
            return "Reservation update failed.";
        }

        // Find the new visitor and the new event based on their IDs
        Visitor newVisitor = visitorServices.getVisitorUsingID(newVisitorId);
        Event newEvent = eventServices.getEventUsingID(newEventId);

        // If the new visitor or event doesn't exist, return silently without an error
        if (newVisitor == null || newEvent == null) {
            return "Reservation update failed.";
        }

        // Check if the new event has available capacity before updating
        if (newEvent.getCurrentCapacity() <= 0) {
            return "Event is fully booked.";
        }

        // Update the visitor and event for the reservation
        reservationToUpdate.setVisitorId(newVisitorId);
        reservationToUpdate.setEventId(newEventId);

        // Call the decreaseCurrentCapacity method to update the new event's capacity
        String capacityUpdateMessage = eventServices.decreaseCurrentCapacity(newEventId);
        if (!capacityUpdateMessage.contains("successfully")) {
            return capacityUpdateMessage;
        }

        return "Reservation updated successfully.";
    }

    // Methodos gia epistrofi olwn twn kratisewn
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations); // Epistrofi antigrafou tis listas gia asfaleia
    }

    // Methodos gia anazitisi kratisewn sugkekrimenou visitor
    public List<Reservation> getReservationsByVisitor(Integer visitorId) {
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        if (visitor == null) {
            return new ArrayList<>(); // An o Visitor den vrethei, epistrefetai adeia lista
        }

        List<Reservation> visitorReservations = new ArrayList<>();

        // Prosthiki kratisewn tou sugkekrimenou visitor sti lista.
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId().equals(visitor)) { // We use equals() instead of '=='
                visitorReservations.add(reservation);
            }
        }

        return visitorReservations;
    }

    // Methodos gia anazitisi kratisewn sugkekrimenou event
    public List<Reservation> getReservationsByEvent(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return new ArrayList<>(); // An to Event den vrethei, epistrefetai adeia lista
        }

        List<Reservation> eventReservations = new ArrayList<>();

        // Prosthiki kratisewn tou event sti lista
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(event)) {
                eventReservations.add(reservation);
            }
        }

        return eventReservations;
    }

    // Methodos gia metrisis kratisewn enos sugkekrimenou event
    public int countReservationsForEvent(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return 0; // An to Event den vrethei, epistrefetai 0
        }

        int count = 0;

        // Auxisi tou metriti gia kathe kratisi pou antistoixei sto event
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(event)) {
                count++;
            }
        }

        return count;
    }
    
    //Vriskei reservation me vash to id tou reservation 
    public Reservation getReservationUsingID (Integer id)
    {
    	for (Reservation temp : reservations) {
    		if (temp.getId().equals(id)) { 
				return temp;
			}
		}
		return null;
    }
    
    // Method which deletes all reservations of one event. Called when Event is Deleted?
    // Den prepei na kanoume alli mia  delete all events of Visitor, otan diagrafetai o Visitor?
    public String cancelAllReservationsForEvent (Integer eventID)
    {
    	Event anEvent = eventServices.getEventUsingID(eventID);
    	 if (anEvent == null) 
    	 {
    		 return "There is no such event!";
    	 }
    	 
    	 List<Reservation> tempReservations = getReservationsByEvent(eventID);
    			 
    	if(tempReservations.isEmpty())
    	{
    		return "This event doesn't have any reservations";

    	}
    				
    	 for (Reservation reservation : tempReservations) {
 	        cancelReservation(reservation.getId());
 	    }		
    				
    	 return "All reservations for this event have been cancelled";
    }
        
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

    
    /*
     * We want visitors to be able to make reservations to Events 
     * just by giving their ID and the title of the Event. This
     * method checks which eventID corresponds to the given title
     * (by calling the getEventIDFromTitle method).
     * 
     * Then, it uses the "original" addReservation method of this class,
     * so that there are no duplicates
     * 
     * (In case of the event not being found, the Event id will be 0.
     * So when the "original" addReservation checks, it will return
     * an error message) 
     */
    public void addReservation (Integer visitorID, String title)
    {
    	Integer eventID = eventServices.getEventIDFromTitle(title);
    	addReservation(visitorID, eventID);
    	
    }
    
    
    /*
     * Takes the ID of a visitor and  then, using the cancelReservation
     * method, it cancels all the reservations they have made.
     */
    public String cancelAllReservationsForVisitor (Integer visitorID)
    {
    	 List<Reservation> tempReservations = getReservationsByVisitor(visitorID);

    	    if (tempReservations.isEmpty()) {
    	        return "This visitor hasn't made any reservations";
    	    }


    	    for (Reservation reservation : tempReservations) {
    	        cancelReservation(reservation.getId());
    	    }

    	    return "All reservations for this visitor have been cancelled.";
    }
    
    
    

}