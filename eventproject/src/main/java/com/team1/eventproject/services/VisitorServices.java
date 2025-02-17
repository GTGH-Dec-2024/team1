package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {

	// The class VisitorServices stores all the visitors in the list allVisitors.
	private ArrayList<Visitor> allVisitors;

	// Constructor
	public VisitorServices(ArrayList<Visitor> allVisitors) {
		this.allVisitors = allVisitors;
	}

	// The method addVisitor creates a new visitor and adds it in the list
	// allVisitors
	public String addVisitor(String name, String surname, String email) {
		/*
		 * We want all IDs to be given automatically. We use a list to help us. If the
		 * list is empty, then we know that it is the first object that will be made so
		 * its id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added, and by
		 * increasing it by 1 we get the new id!
		 */
		Integer id;
		if (allVisitors.isEmpty()) {
			id = 1; // If the list allVisitors is empty, then it will be the first object, so id=1
		} else {
			id = allVisitors.get(allVisitors.size() - 1).getId() + 1; // If the list is not empty the id will be equal
																		// to the id of the last object +1.
		}

		/*
		 * We consider the email to be unique, so we check if the visitor is already on
		 * the list using the email.
		 */
		for (Visitor visitor : allVisitors) {
			if (visitor.getEmail().equalsIgnoreCase(email)) {
				return "The Visitor: " + name + " " + surname + " has already been added";
			}

		}

		// We create a new Visitor object
		Visitor temp = new Visitor(name, surname, email, id);

		allVisitors.add(temp);

		return "The Visitor " + temp.getName() + " " + temp.getSurname() + " has been added successfully "
				+ "and given the ID " + id;
	}

	// Method which returns visitor based on his id
	public Visitor getVisitorUsingID(Integer id) {
		for (Visitor visitor : allVisitors) // for every visitor in the list all visitor
		{
			if (visitor.getId().equals(id)) { // Changed == to .equals() for Integer comparison

				return visitor;
			}
		}
		return null;
	}

	// Method to get all visitors, returns a list with all the visitors of all
	// events.
	public List<Visitor> getAllVisitors() {
		return new ArrayList<>(allVisitors); // Returns a copy of the list AllVisitors to ensure that the list will not
												// be changed.
		// Returns a list whivh has the same elements as the allVisitors list but is
		// independent from it.
	}

	// Method to get all visitors minus the ones that have been deleted
	public List<Visitor> getAllActiveVisitors() {
			
		List<Visitor> activeVisitors = new ArrayList<>();

			for (Visitor vs : allVisitors) {
				if (vs.getStatus().equalsIgnoreCase("active")) {
					activeVisitors.add(vs);
				}

			}
		return activeVisitors;
	}

	/*
	 * Method to update a visitor This methods takes as attributes the id of the
	 * visitor that will be changed, his new Name, Surname and Email and returns a
	 * visitor with new attributes.
	 */
	public String updateVisitor(Integer id, String newName, String newSurname, String newEmail) {

		for (Visitor visitor : allVisitors) {
			if (visitor.getId().equals(id)) {
//        		if (!visitor.getStatus().equals("active"))
//        			return "The visitor has been deleted, you can no longer update them";
				if (newName != null)
					visitor.setName(newName);
				if (newSurname != null)
					visitor.setSurname(newSurname);
				if (newEmail != null)
					visitor.setEmail(newEmail);

				return "The visitor has been updated";
			}
		}
		return "The visitor ID you provided is not correct";
	}

	/*
	 * Method to delete a visitor. When a visitor gets deleted, his reservations do
	 * not get affected, as his tickets have already been bought.
	 */

	// Method to delete a visitor
	public String deleteVisitor(Integer visitorId) {
		Visitor visitor = getVisitorUsingID(visitorId);
		if (visitor == null || visitor.getStatus().equalsIgnoreCase("deleted")) {
			return "The Visitor with ID " + visitorId + " doesn't exist or has already been deleted";
		}

		visitor.setStatus("deleted"); // Mark as deleted
		return "The Visitor with ID " + visitorId + " has been deleted";
	}

}
