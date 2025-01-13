package com.team1.eventproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.services.ReservationServices;

@RestController // Decleres that this class is a REST Controller.
@RequestMapping("reservations") // Defines the base URL for all the endpoints of this class
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices; // Injection of service ReservationServices

    
    // Endpoint for GET in /reservations/all
    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationServices.getAllReservations(); // Returns all the reservations
    }

    // Endpoint for POST in /reservations/add
    @PostMapping("/add")
    public String addReservation(@RequestParam Integer visitorId, @RequestParam Integer eventId) {
        return reservationServices.addReservation(visitorId, eventId); // adds a new reservation
    }

    // Endpoint for DELETE in /reservations/cancel
    @DeleteMapping("/cancel")
    public String cancelReservation(@RequestParam Integer reservationId) {
        return reservationServices.cancelReservation(reservationId); // cancels reservation
    }
    
    // Endpoint for UPDATE in /reservations/update/{id}
    @PutMapping("/update/{id}")
    public String updateReservation(
            @PathVariable Integer id,
            @RequestParam Integer newVisitorId,
            @RequestParam Integer newEventId) {
        return reservationServices.updateReservation(id, newVisitorId, newEventId);
    }
    
    
    // Endpoint for GET in /reservations/visitor
    @GetMapping("/visitor")
    public List<Reservation> getReservationsByVisitor(@RequestParam Integer visitorId) {
        return reservationServices.getReservationsByVisitor(visitorId); // Returns the reservations for a specific visitor
    }

    // Endpoint for GET in /reservations/event
    @GetMapping("/event")
    public List<Reservation> getReservationsByEvent(@RequestParam Integer eventId) {
        return reservationServices.getReservationsByEvent(eventId); // Returns the reservations for a specific event
    }

    // Endpoint for GET in /reservations/event/count
    @GetMapping("/event/count")
    public int countReservationsForEvent(@RequestParam Integer eventId) {
        return reservationServices.countReservationsForEvent(eventId); // Returns the number of reservations for a specific event
    }

    // Endpoint for GET in /reservations/{id}
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable Integer id) {
        return reservationServices.getReservationUsingID(id); // Returns a reservation based on its ID
    }
    
    // Endpoint for DELETE in /delete/event/{eventId}
    @DeleteMapping("/delete/event/{eventId}")
    public String cancelAllReservationsOfEvent(@PathVariable Integer eventId) {
        return reservationServices.cancelAllReservationsForEvent(eventId);
    }
    
    /*
     * Endpoint to add a reservation using visitor ID and event title.
     *
     * @param visitorID The ID of the visitor making the reservation.
     * @param title The title of the event for the reservation.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addReservation(@RequestParam Integer visitorID, @RequestParam String title) {
        try {
            reservationServices.addReservation(visitorID, title);
            return ResponseEntity.ok("Reservation added successfully for visitor ID: " + visitorID + " and event: " + title);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add reservation: " + e.getMessage());
        }
    }

    /*
     * Endpoint to cancel all reservations for a specific visitor.
     *
     * @param visitorID The ID of the visitor whose reservations will be canceled.
     * @return Response message indicating success or failure.
     */
    @DeleteMapping("/cancel/all")
    public ResponseEntity<String> cancelAllReservationsForVisitor(@RequestParam Integer visitorID) {
        String response = reservationServices.cancelAllReservationsForVisitor(visitorID);
        if (response.contains("hasn't made any reservations")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
}