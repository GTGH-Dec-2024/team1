package com.team1.eventproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.entities.Reservation;

@Service
public class ReservationManagerServices {

    @Autowired
    private EventServices EventServices;

    @Autowired
    private ReservationServices ReservationServices;

    @Autowired
    private VisitorServices VisitorServices;

    //EDW THA MPEI H METHODOS VISITORSPEREVENT
    public void visitorsPerEvent() {

		for (Event event : EventServices.getAllEvents()) {
			System.out.println("Event: " + event.getTitle());
			List<Reservation> reservationsForThisEvent = ReservationServices.getReservationsByEvent(event.getId());
			if (reservationsForThisEvent.isEmpty()) {
				System.out.println("No visitors yet!");
			} else {
				for (Reservation reservation : reservationsForThisEvent) {
					System.out.println(reservation.getVisitor().getName() + " " + reservation.getVisitor().getSurname()
							+ " (" + reservation.getVisitor().getId() + ")");
				}
			}
		}
	}

	public void getReservationsForOrganizersEvents() {

	}
}