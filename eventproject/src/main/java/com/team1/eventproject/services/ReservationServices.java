package com.team1.eventproject.services;

import java.util.ArrayList;

/*import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;*/


import java.util.List;

// import javax.swing.text.Document;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service 
public class ReservationServices {

    private List<Reservation> reservations; // A list which stores all the reservations

    @Autowired
    private EventServices eventServices; // Dependency injection from the class EventServices

    @Autowired
    private VisitorServices visitorServices; // Dependency injection from the class VisitorServices

    
    // Constructor for the list 
    public ReservationServices() {
        this.reservations = new ArrayList<>();
    }

    // Method which adds a new reservation to the list reservation by taking the visitor id and event id.
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
            if (reservation.getVisitorId().equals(visitorId) && reservation.getEventId().equals(eventId)) {
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

        // Create a new reservation for the visitor and the event and add it to the list
        Reservation newReservation = new Reservation(visitorId, eventId, id);
        reservations.add(newReservation);

        // Call the decreaseCurrentCapacity method from EventServices
        String capacityUpdateMessage = eventServices.decreaseCurrentCapacity(eventId);
       
        if (!capacityUpdateMessage.contains("successfully")) {
            // If capacity decrease fails, remove the reservation to maintain consistency
            reservations.remove(newReservation);
            return capacityUpdateMessage;
        }

        return "Reservation made successfully for event: " + event.getTitle() +" The ID of the"
        		+ "reservation is: " + id;
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

    // Method which updates a Reservation
    public String updateReservation(Integer reservationId, Integer newVisitorId, Integer newEventId) {
        
    	for (Reservation reservation : reservations) {
            if (reservation.getId().equals(reservationId)) {
                
            	if (newVisitorId != null) {
                    reservation.setVisitorId(newVisitorId);
                }
                if (newEventId != null) {
                    reservation.setEventId(newEventId);
                }
                return "The reservation has been updated.";
            }
        }
        return "The reservation ID you provided is not correct.";
    }

    // Method which returns a list with all reservations of all events.
    public List<Reservation> getAllReservations() {
        return reservations;
    }

    
    // Method to get a visitor's reservation by giving his id.
    public List<Reservation> getReservationsByVisitor(Integer visitorId) {
        
    	Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        if (visitor == null) {
            return new ArrayList<>(); // If the visitor is not found a null list will be returned.
        }

        List<Reservation> visitorReservations = new ArrayList<>();

        // Add reservations of a specific visitor in the list.
        for (Reservation reservation : reservations) {
            if (reservation.getVisitorId().equals(visitorId)) { // We use equals() instead of '=='
                visitorReservations.add(reservation);
            }
        }

        return visitorReservations;
    }

    // Method which gets the reservations for a specific event, given the event id.
    public List<Reservation> getReservationsByEvent(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return new ArrayList<>(); // If the Event is not found an empty list will be returned
        }

        List<Reservation> eventReservations = new ArrayList<>();

        // Add reservations of the event in the list.
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(eventId)) {
                eventReservations.add(reservation);
            }
        }

        return eventReservations;
    }

    // Method which counts the reservations of specific event
    public String countReservationsForEvent(Integer eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return "Event not found, the id you entered is not valid"; // If the event in not found return 0.
        }

        Integer count = 0;

        // Increase the counter for every reservation which corresponds to the event.
        for (Reservation reservation : reservations) {
            if (reservation.getEventId().equals(eventId)) {
                count++;
            }
        }

        return "The Event with id " +eventId+" has "+count+ " reservations!";
    }
    
    
    // Finds a reservation given the reservation's id.
    public Reservation getReservationUsingID (Integer id)
    {
    	for (Reservation temp : reservations) {
    		if (temp.getId().equals(id)) { 
				return temp;
			}
		}
		return null;
    }
    
    // Method which deletes all reservations of one event. Called when Event is Deleted.
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
     * We want visitors to be able to make reservations to Events just by giving their ID and the title of the Event. This
     * method checks which eventID corresponds to the given title (by calling the getEventIDFromTitle method).
     * 
     * Then, it uses the "original" addReservation method of this class, so that there are no duplicates
     * 
     * (In case of the event not being found, the Event id will be 0. So when the "original" addReservation checks,
     * it will return an error message) 
     */
    public void addReservation (Integer visitorID, String title)
    {
    	Integer eventID = eventServices.getEventIDFromTitle(title);
    	addReservation(visitorID, eventID);
    	
    }
    
    
    /*
     * Takes the ID of a visitor and  then, using the cancelReservation method, it cancels all the reservations they have made.
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