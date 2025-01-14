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
     * - URL: /visitors/{id}
     * - Path Variable: {id} - The ID of the visitor to fetch.
     * - Description: Returns the visitor's details if found, or a message if not.
     */
    @GetMapping("/{id}")
    public String getVisitorById(@PathVariable int id) {
        Visitor visitor = visitorServices.getVisitorUsingID(id);
        return (visitor != null)
                ? visitor.toString() // If the visitor is found, return their details.
                : "Visitor not found!"; // Otherwise, return an error message.
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
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email) {
        visitorServices.addVisitor(name, surname, email);
        return "Visitor added successfully!";
    }

    /*
     * Update an existing visitor.
     * - HTTP Method: PUT
     * - URL: /visitors/{id}
     * - Path Variable: {id} - The ID of the visitor to update.
     * - Request Parameters: newName, newSurname, newEmail.
     * - Description: Updates the visitor's details and returns a message.
     */
    @PutMapping("/{id}")
    public String updateVisitor(
            @PathVariable int id,
            @RequestParam String newName,
            @RequestParam String newSurname,
            @RequestParam String newEmail) {
        return visitorServices.updateVisitor(id, newName, newSurname, newEmail)
                ? "Visitor updated successfully!" // If update is successful.
                : "Visitor not found!"; // If the visitor does not exist.
    }

    /*
     * Delete a visitor by ID.
     * - HTTP Method: DELETE
     * - URL: /visitors/{id}
     * - Path Variable: {id} - The ID of the visitor to delete.
     * - Description: Deletes the visitor and cancels their reservations.
     */
    @DeleteMapping("/{id}")
    public String deleteVisitor(@PathVariable int id) {
        return visitorServices.deleteVisitor(id)
                ? "Visitor deleted successfully!" // If deletion is successful.
                : "Visitor not found or already deleted!"; // If the visitor does not exist or is already deleted.
    }
}
