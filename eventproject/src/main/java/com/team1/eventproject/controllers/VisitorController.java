package com.team1.eventproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.services.VisitorServices;

import java.util.List;

@RestController
@RequestMapping("visitors")
public class VisitorController {
	@Autowired
    private VisitorServices visitorServices;

    // Endpoint to get all visitors
    @GetMapping
    public ResponseEntity<List<Visitor>> getAllVisitors() {
        List<Visitor> visitors = visitorServices.getAllVisitors();
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    // Endpoint to get a visitor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Visitor> getVisitorById(@PathVariable int id) {
        Visitor visitor = visitorServices.getVisitorUsingID(id);
        if (visitor != null) {
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to add a new visitor
    @PostMapping
    public ResponseEntity<List<Visitor>> addVisitor(
            @RequestParam String name,
            @RequestParam String surname,
            @RequestParam String email) {
        List<Visitor> updatedVisitors = visitorServices.addVisitor(name, surname, email);
        return new ResponseEntity<>(updatedVisitors, HttpStatus.CREATED);
    }

    // Endpoint to update a visitor by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateVisitor(
            @PathVariable int id,
            @RequestParam String newName,
            @RequestParam String newSurname,
            @RequestParam String newEmail) {
        boolean updated = visitorServices.updateVisitor(id, newName, newSurname, newEmail);
        if (updated) {
            return new ResponseEntity<>("Visitor updated successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Visitor not found.", HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a visitor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVisitor(@PathVariable int id) {
        boolean deleted = visitorServices.deleteVisitor(id);
        if (deleted) {
            return new ResponseEntity<>("Visitor deleted successfully.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Visitor not found.", HttpStatus.NOT_FOUND);
    }

}
