package com.team1.eventproject.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;

@Service
public class OrganizerServices {
	private ArrayList<Organizer> allOrganizers;

	@Autowired
	private EventServices eventServices;

	public OrganizerServices(ArrayList<Organizer> allOrganizers) {
		this.allOrganizers = new ArrayList<>();
	}

	public String addOrganizer(String name, String surname, String afm, String description) {
		
		/*
		 * AFM is unique to each organizer. Therefore, we use it to check if an organizer has
		 * already been added to the database
		 */
		for (Organizer organizer : allOrganizers) {
			if (organizer.getAfm().equals(afm)) {
				return "The Organizer: " + name + " " + surname + " has already been added";
			}
		}
		/*
		 * We want all IDs to be given automatically. Therefore, we use the
		 * allOrganizers list to help us. If the list is empty, then we know it is the
		 * first object that will be made so its id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added, and by
		 * increasing it by 1 we get the new id!
		 * 
		 */
		Integer id;
		if (allOrganizers.isEmpty()) {
			id = 1;

		} else {
			id = allOrganizers.get(allOrganizers.size() - 1).getId() + 1;
		}

		Organizer temp = new Organizer(name, surname, afm, description, id);

		allOrganizers.add(temp);
		return "The Organizer: " + temp.getName() + temp.getSurname() + " has been added succesfully"
				+ " and given the ID " + id;

	}

	public Organizer getOrganizerUsingID(Integer id) {
		for (Organizer organizer : allOrganizers) {
			if (id.equals(organizer.getId())) {
				return organizer;
			}
		}
		return null;
	}

	/*
	 * When an organizer gets deleted, their status becomes "deleted". All of their
	 * upcoming Events also get deleted.
	 * 
	 */
	public String deleteOrganizer(Integer organizerId) {
		Organizer temp = getOrganizerUsingID(organizerId);

		if (temp.equals(null) || temp.getStatus().equalsIgnoreCase("deleted"))
			return "Organizer not found or has already been deleted";

		temp.setStatus("deleted");

		String message = eventServices.cancelAllEventsForOrganizer(organizerId);
		return "Organizer " + temp.getName() + " has been deleted. " + message;

	}

	public ArrayList<Organizer> getAllOrganizers() {
		return allOrganizers;
	}
	
	/*
	 * The organizer list contains all the organizers, even the 
	 * ones that have been deleted (status.equalsIgnoreCase("deleted")
	 * The following method returns a list with the organizers that
	 * are active
	 * 
	 */
	public List<Organizer> getAllActiveOrganizers() {

	    List<Organizer> activeOrganizers = new ArrayList<>();

	    for (Organizer om : allOrganizers) {
	        if (om.getStatus().equalsIgnoreCase("active")) {
	        	activeOrganizers.add(om);
	        }
	    }

	    return activeOrganizers;
	}

	/*
	 * public String deleteOrganizer(Integer organizerId) { String message;
	 * 
	 * for(Organizer organizer : allOrganizers) {
	 * if(organizerId.equals(organizer.getId())) { allOrganizers.remove(organizer);
	 * message="Organizer removed successfully!"; }else {
	 * message="Organizer not found!"; } } return message; }
	 */

}
