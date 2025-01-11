package com.team1.eventproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.services.ReservationServices;

@RestController // Decleres that this class is a REST Controller.
@RequestMapping("reservations") // Defines the base URL for all the endpoints of this class
public class ReservationController {

    @Autowired
    private ReservationServices reservationServices; // Injection of service ReservationServices

    // Endpoint for GET in /reservations/hello
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Reservation Controller";
    }

    // Endpoint for GET in /reservations/all
    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationServices.getAllReservations(); // Returns all the reservations
    }

    // Endpoint for POST in /reservations/add
    @PostMapping("/add")
    public String addReservation(@RequestParam int visitorId, @RequestParam int eventId) {
        return reservationServices.addReservation(visitorId, eventId); // Προσθέτει μια νέα κράτηση
    }

    // Endpoint for DELETE in /reservations/cancel
    @DeleteMapping("/cancel")
    public String cancelReservation(@RequestParam int visitorId, @RequestParam int eventId) {
        return reservationServices.cancelReservation(visitorId, eventId); // cancels reservation
    }

    // Endpoint for GET in /reservations/visitor
    @GetMapping("/visitor")
    public List<Reservation> getReservationsByVisitor(@RequestParam int visitorId) {
        return reservationServices.getReservationsByVisitor(visitorId); // Returns the reservations for a specific visitor
    }

    // Endpoint for GET in /reservations/event
    @GetMapping("/event")
    public List<Reservation> getReservationsByEvent(@RequestParam int eventId) {
        return reservationServices.getReservationsByEvent(eventId); // Returns the reservations for a specific event
    }

    // Endpoint for GET in /reservations/event/count
    @GetMapping("/event/count")
    public int countReservationsForEvent(@RequestParam int eventId) {
        return reservationServices.countReservationsForEvent(eventId); // Returns the number of reservations for a specific event
    }

    // Endpoint για GET στο /reservations/{id}
    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationServices.getReservationUsingID(id); // Returns a reservation based on its ID
    }
}