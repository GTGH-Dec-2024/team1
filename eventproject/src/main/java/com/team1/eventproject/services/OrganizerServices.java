package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;


@Service
public class OrganizerServices {
	private ArrayList<Organizer> allOrganizers;
	
	 @Autowired
	    private EventServices eventServices;
	 
	 @Autowired
	    private ReservationServices reservationServices;
	 
	    // Method to get participants of an event by organizer ID and event ID
	    // O Organizer vlepei lista participant gia tin ekdilosi tou. Sugekrimeni?
	    public List<Visitor> getEventParticipants(Integer organizerId, Integer eventId) {
	        // Find the event based on the eventId and organizerId
	        Event event = eventServices.getEventUsingID(eventId);

	        if (event == null) {
	            throw new IllegalArgumentException("Event with ID " + eventId + " not found.");
	        }

	        if (!event.getOrganizerId().equals(organizerId)) {
	            throw new IllegalArgumentException("Event does not belong to the organizer with ID " + organizerId);
	        }

	        // Get all reservations for this event
	        List<Reservation> reservations = reservationServices.getReservationsByEvent(eventId);
	        
	        // Get the list of visitors (participants) from the reservations
	        List<Visitor> participants = reservations.stream()
	            .map(Reservation::getVisitor) // Get the visitor from each reservation
	            .collect(Collectors.toList());

	        return participants; // Return the list of participants (Visitors)
	    }
	
	     /*
	     * Adds a new Organizer to the list.
	     * Returns a success message or error message if the organizer already exists.
	     */
	    public String addOrganizer(String name, String surname, String afm, String description) {
	        Integer id;
	        if (allOrganizers.isEmpty()) {
	            id = 1; // Assign ID as 1 for the first organizer
	        } else {
	            id = allOrganizers.get(allOrganizers.size() - 1).getId() + 1; // Increment ID
	        }

	        Organizer temp = new Organizer(name, surname, afm, description, id);

	        if (!allOrganizers.contains(temp)) {
	            allOrganizers.add(temp); // Add organizer if not already present
	            return "Organizer " + temp.getName() + " " + temp.getSurname() + " added successfully.";
	        } else {
	            return "The Organizer: " + temp.getName() + " " + temp.getSurname() + " already exists.";
	        }
	    }

	
	
	    /*
	     * Retrieves an Organizer by their ID.
	     * Returns the Organizer or null if not found.
	     */
	    public Organizer getOrganizerUsingID(Integer id) {
	        for (Organizer organizer : allOrganizers) {
	            if (organizer.getId().equals(id)) { // Use equals() for Integer comparison
	                return organizer;
	            }
	        }
	        return null; // Organizer not found
	    }
	    
	    /*
	     * Updates an Organizer's details.
	     * Returns a success or error message.
	     */
	    public String updateOrganizer(Integer id, String newName, String newSurname, String newAfm, String newDescription) {
	        Organizer organizer = getOrganizerUsingID(id);

	        if (organizer == null) {
	            return "Organizer with ID " + id + " not found.";
	        }

	        // Update details
	        organizer.setName(newName);
	        organizer.setSurname(newSurname);
	        organizer.setAfm(newAfm);
	        organizer.setDescription(newDescription);

	        return "Organizer with ID " + id + " updated successfully.";
	    }
	
	
	/*
	 * When an organizer gets deleted, their status becomes "deleted".
	 * All of their upcoming Events also get deleted.
	 * 
	 */
	  public String deleteOrganizer(Integer organizerId) {
	    	Organizer temp = getOrganizerUsingID(organizerId);
			
	    	if (temp == null || temp.getStatus().equalsIgnoreCase("deleted"))
				return "Organizer not found or has already been deleted";
		
			temp.setStatus("deleted");
			
			String message = eventServices.cancelAllEventsForOrganizer(organizerId);
			return "Organizer " + temp.getName() +" has been deleted. "+ message;
	        
	    }
	
	  /*
	     * Retrieves all Organizers.
	     * Returns the list of all organizers.
	     */
	    public List<Organizer> getAllOrganizers() {
	        return new ArrayList<>(allOrganizers); // Return a copy to ensure immutability
	    }

	
}
