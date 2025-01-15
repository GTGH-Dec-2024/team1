package com.team1.eventproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.services.ReservationManagerServices;
import com.team1.eventproject.services.ReservationServices;

@RestController
@RequestMapping("reservations")
public class ReservationController {

	@Autowired
	ReservationServices reservationServices;
	@Autowired
	ReservationManagerServices reservationManagerServices; 

	@PostMapping("/addReservationByEvendId")
	public String addReservation(@RequestParam Integer visitorId, @RequestParam Integer eventId) {
		return reservationServices.addReservation(visitorId, eventId);
	}

	@PostMapping("/addReservationByEventTitle")
	public String addReservation(@RequestParam Integer visitorID, @RequestParam String title) {
		return reservationServices.addReservation(visitorID, title);
	}

	@PutMapping("/update")
	public String updateReservation(@RequestParam (required=false) Integer reservationId, @RequestParam (required=false) Integer newVisitorId,
			@RequestParam Integer newEventId) {
		return reservationServices.updateReservation(reservationId, newVisitorId, newEventId);
	}

	@DeleteMapping("/cancelReservationWithId")
	public String cancelReservation(@RequestParam Integer reservationId) {
		return reservationServices.cancelReservation(reservationId);
	}

	@DeleteMapping("/cancelAllForReservationsForEvent")
	public String cancelAllReservationsForEvent(@RequestParam Integer eventID) {
		return reservationServices.cancelAllReservationsForEvent(eventID);
	}

	@GetMapping("/getAllReservations")
	public List<Reservation> getAllReservations() {
		return reservationServices.getAllReservations();
	}

	@GetMapping("/getReservationForEvent")
	public List<Reservation> getReservationsByEvent(@RequestParam Integer eventId) {
		return reservationServices.getReservationsByEvent(eventId);
	}

	@GetMapping("/cancelAllForReservationsForVisitor")
	public String cancelAllReservationsForVisitor(@RequestParam Integer visitorID) {
		return reservationServices.cancelAllReservationsForVisitor(visitorID);
	}

	@GetMapping("/getReservationWithId")
	public Reservation getReservationUsingID(@RequestParam Integer id) {
		return reservationServices.getReservationUsingID(id);
	}

	@GetMapping("/getNumberOfReservationsForEvent")
	public String countReservationsForEvent(@RequestParam Integer eventId) {
		return reservationServices.countReservationsForEvent(eventId);
	}
	
	@GetMapping("/getVisitorsPerEvent")
	public String visitorsPerEvent() {
		return reservationManagerServices.visitorsPerEvent();
	}

}