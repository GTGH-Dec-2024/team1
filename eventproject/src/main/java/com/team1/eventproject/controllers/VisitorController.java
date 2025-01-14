package com.team1.eventproject.controllers;

import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.services.VisitorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Declares this class as a REST controller.
@RequestMapping("/visitors") // Base URL for all visitor-related endpoints.
public class VisitorController {

    @Autowired // Automatically injects the VisitorServices dependency.
    private VisitorServices visitorServices;

    /*
     * Get all visitors.
     * - HTTP Method: GET
     * - URL: /visitors
     * - Description: Returns a list of all visitors in the system.
     */
    @GetMapping
    public List<Visitor> getAllVisitors() {
        return visitorServices.getAllVisitors(); // Fetch and return all visitors.
    }

    /*
     * Get a visitor by ID.
     * - HTTP Method: GET
     * - URL: /visitors/by-id
     * - Request Parameter: id - The ID of the visitor to fetch.
     * - Description: Returns the visitor's details if found, or a message if not.
     */
    @GetMapping("/by-id")
    public String getVisitorById(@RequestParam(required = false) Integer id) {
        // Check if the ID is null.
        if (id == null) {
            return "Error: Visitor ID must be provided!"; // Return an error message if ID is missing.
        }

        // Try to fetch the visitor based on ID.
        Visitor visitor = visitorServices.getVisitorUsingID(id);
        return (visitor != null)
                ? visitor.toString() // If found, return visitor details.
                : "Visitor not found!"; // If not found, return an error message.
    }

    /*
     * Add a new visitor.
     * - HTTP Method: POST
     * - URL: /visitors
     * - Request Parameters: name, surname, email.
     * - Description: Adds a new visitor and returns a success message.
     */
    @PostMapping
    public String addVisitor(
            @RequestParam String name,     // Name of the visitor.
            @RequestParam String surname,  // Surname of the visitor.
            @RequestParam String email) {  // Email of the visitor.
        visitorServices.addVisitor(name, surname, email); // Add the visitor.
        return "Visitor added successfully!"; // Return success message.
    }

    /*
     * Update an existing visitor.
     * - HTTP Method: PUT
     * - URL: /visitors
     * - Request Parameters: id, newName, newSurname, newEmail.
     * - Description: Updates the visitor's details and returns a message.
     */
    @PutMapping
    public String updateVisitor(
            @RequestParam(required = false) Integer id,  // ID of the visitor to update (optional).
            @RequestParam String newName,                 // New name of the visitor.
            @RequestParam String newSurname,              // New surname of the visitor.
            @RequestParam String newEmail) {              // New email of the visitor.
        
        // Check if the ID is null.
        if (id == null) {
            return "Error: Visitor ID must be provided!"; // Return an error message if ID is missing.
        }

        // Try to update the visitor.
        return visitorServices.updateVisitor(id, newName, newSurname, newEmail)
                ? "Visitor updated successfully!" // If update is successful, return success message.
                : "Visitor not found!"; // If the visitor does not exist, return error message.
    }

    /*
     * Delete a visitor by ID.
     * - HTTP Method: DELETE
     * - URL: /visitors
     * - Request Parameter: id - The ID of the visitor to delete.
     * - Description: Deletes the visitor and cancels their reservations.
     */
    @DeleteMapping
    public String deleteVisitor(@RequestParam(required = false) Integer id) {
        // Check if the ID is null.
        if (id == null) {
            return "Error: Visitor ID must be provided!"; // Return an error message if ID is missing.
        }

        // Try to delete the visitor.
        return visitorServices.deleteVisitor(id)
                ? "Visitor deleted successfully!" // If deletion is successful, return success message.
                : "Visitor not found or already deleted!"; // If the visitor does not exist or is already deleted.
    }
}