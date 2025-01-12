package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.team1.eventproject.entities.Reservation;
import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Event;

@Service 
public class ReservationServices {

    private List<Reservation> reservations; // Lista pou apothikeuei oles tis kratiseis

    @Autowired
    private EventServices eventServices; // Exartisi gia ta event services

    @Autowired
    private VisitorServices visitorServices; // Exartisi gia ta visitor services

    
    // Constructor gia tin arxikopoiisi tis listas
    public ReservationServices() {
        this.reservations = new ArrayList<>();
    }

    
    // Methodos gia prosthiki neas kratisis tou visitor sto event.
    public String addReservation(int visitorId, int eventId) {
        // Vres ton Visitor kai to Event me vasi ta IDs
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        Event event = eventServices.getEventUsingID(eventId);

        // Elegxos an vrethikan o Visitor kai to Event
        if (visitor == null) {
            return "Visitor with ID " + visitorId + " not found.";
        }
        if (event == null) {
            return "Event with ID " + eventId + " not found.";
        }

        // Elegxos an yparxei idi kratisi gia ton visitor kai to event
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
                return "This reservation already exists.";
            }
        }
        
        /*
		 * We want all IDs to be given automatically. Therefore, we
		 * use the allEmployees list to help us. If the list is empty,
		 * then we know it is the first object that will be made so its
		 * id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added,
		 * and by increasing it by 1 we get the new id!
		 * 
		 */
		int id; 
		if(reservations.isEmpty()){
			id = 1;
			
		}else
		{
			id = reservations.get(reservations.size() - 1).getId() + 1;
		}
        
        // Dimiourgia neas kratisis tou visitor gia to event kai prosthiki sti lista
        Reservation newReservation = new Reservation(visitor, event, id);
        reservations.add(newReservation);
        return "Reservation made successfully for event: " + event.getTitle();
    }

 
    
    // Methodos gia akyrwsi kratisis tou visitor sto event.
    public String cancelReservation(int visitorId, int eventId) {
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        Event event = eventServices.getEventUsingID(eventId);

        // Elegxos an vrethikan o Visitor kai to Event
        if (visitor == null) {
            return "Visitor with ID " + visitorId + " not found.";
        }
        if (event == null) {
            return "Event with ID " + eventId + " not found.";
        }

        Reservation reservationToCancel = null;

        // Anazitisi tis kratisis sti lista
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor) && reservation.getEvent().equals(event)) {
                reservationToCancel = reservation;
                break;
            }
        }

        // An vrethei i kratisi, afaireitai apo ti lista
        if (reservationToCancel != null) {
            reservations.remove(reservationToCancel);
            return "Reservation cancelled for event: " + event.getTitle();
        }

        // Epistrofi minimatos an den vrethei i kratisi
        return "Reservation not found in order to be canceled.";
    }

    // Methodos gia epistrofi olwn twn kratisewn
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations); // Epistrofi antigrafou tis listas gia asfaleia
    }

    // Methodos gia anazitisi kratisewn sugkekrimenou visitor
    public List<Reservation> getReservationsByVisitor(int visitorId) {
        Visitor visitor = visitorServices.getVisitorUsingID(visitorId);
        if (visitor == null) {
            return new ArrayList<>(); // An o Visitor den vrethei, epistrefetai adeia lista
        }

        List<Reservation> visitorReservations = new ArrayList<>();

        // Prosthiki kratisewn tou sugkekrimenou visitor sti lista.
        for (Reservation reservation : reservations) {
            if (reservation.getVisitor().equals(visitor)) {
                visitorReservations.add(reservation);
            }
        }

        return visitorReservations;
    }

    // Methodos gia anazitisi kratisewn sugkekrimenou event
    public List<Reservation> getReservationsByEvent(int eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return new ArrayList<>(); // An to Event den vrethei, epistrefetai adeia lista
        }

        List<Reservation> eventReservations = new ArrayList<>();

        // Prosthiki kratisewn tou event sti lista
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                eventReservations.add(reservation);
            }
        }

        return eventReservations;
    }

    // Methodos gia metrisis kratisewn enos sugkekrimenou event
    public int countReservationsForEvent(int eventId) {
        Event event = eventServices.getEventUsingID(eventId);
        if (event == null) {
            return 0; // An to Event den vrethei, epistrefetai 0
        }

        int count = 0;

        // Auxisi tou metriti gia kathe kratisi pou antistoixei sto event
        for (Reservation reservation : reservations) {
            if (reservation.getEvent().equals(event)) {
                count++;
            }
        }

        return count;
    }
    
    //Vriskei reservation me vash to id tou reservation 
    public Reservation getReservationUsingID (int id)
    {
    	for (Reservation temp : reservations) {
			if (temp.getId() == id) {
				return temp;
			}
		}
		return null;
    }
}