package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {
    // The class VisitorServices stores all the visitors in the list allVisitors.
    private ArrayList<Visitor> allVisitors;

    @Autowired
    private ReservationManagerServices reservationManagerServices; // Updated to use ReservationManagerServices without Lazy

    // Constructor
    public VisitorServices(ArrayList<Visitor> allVisitors) {
        this.allVisitors = allVisitors;
    }

    // Method addVisitor adds a new visitor to the allVisitors list
    public List<Visitor> addVisitor (String name, String surname, String email) {
        int id;
        if(allVisitors.isEmpty()) {
            id = 1;
        } else {
            id = allVisitors.get(allVisitors.size() - 1).getId() + 1;
        }

        Visitor temp = new Visitor(name, surname, email, id);
        if (!allVisitors.contains(temp)) {
            allVisitors.add(temp);
        } else {
            System.out.println("The Visitor: " + temp.getName() + " " + temp.getSurname() + " has already been added");
        }
        return allVisitors;
    }

    // Method which returns visitor based on his id
    public Visitor getVisitorUsingID(Integer id) {
        for (Visitor visitor : allVisitors) {
            if (visitor.getId().equals(id)) {
                return visitor;
            }
        }
        return null;
    }

    // Method to get all visitors
    public List<Visitor> getAllVisitors() {
        return new ArrayList<>(allVisitors); // Return a copy to ensure immutability
    }

    // Method to update a visitor
    public Boolean updateVisitor(Integer id, String newName, String newSurname, String newEmail) {
        Visitor visitor = getVisitorUsingID(id);
        if (visitor != null) {
            visitor.setName(newName);
            visitor.setSurname(newSurname);
            visitor.setEmail(newEmail);
            return Boolean.TRUE; // Update successful
        }
        return Boolean.FALSE; // Visitor not found
    }

    // Method to delete a visitor
    public boolean deleteVisitor(Integer visitorId) {
        Visitor visitor = getVisitorUsingID(visitorId);
        if (visitor == null || visitor.getStatus().equalsIgnoreCase("deleted")) {
            return false; // Visitor not found or already deleted
        }

        visitor.setStatus("deleted"); // Mark as deleted
        reservationManagerServices.cancelAllReservationsForVisitor(visitorId); // Now using ReservationManagerServices
        return true; // Deletion successful
    }
}
