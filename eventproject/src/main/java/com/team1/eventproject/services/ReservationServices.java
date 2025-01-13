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
            
            //EVA:Using eventServices, update currentCapacity of Event!
            
        }
        
        /*
		 * We want all IDs to be given automatically. Therefore, we
		 * use the reservations list to help us. If the list is empty,
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
        // Call method decreaseCurrentCapacity from EventServices
        return "Reservation made successfully for event: " + event.getTitle();
        
    }

     // ERWTHSH: An theloume na akurwsoume to reservation enos sugekrimenou visitor h event pws tha to kanoume?
     // Tha prepei kapws na vriskoume to id tou reservation se poio visitor kai enent id antistoixei?
    
    // Methodos gia akyrwsi kratisis me vasi to ID tis kratisis
    public String cancelReservation(int reservationId) {
        // Vres tin kratisi me vasi to ID
        Reservation reservationToCancel = getReservationUsingID(reservationId);

        // Elegxos an vrethike i kratisi
        if (reservationToCancel == null) {
            return "Reservation with ID " + reservationId + " not found.";
        }

        // Pare to Event tis kratisis gia na enimeroseis ti xoritikotita
        Event event = reservationToCancel.getEvent();

        // Afairesh tis kratisis apo ti lista
        reservations.remove(reservationToCancel);
        // Call increaseCurrentCapacity from EventServices
          

        return "Reservation with ID " + reservationId + " cancelled successfully for event: " + event.getTitle();
    }
    
    // Methodos gia enimerosi kratisis
    public String updateReservation(int reservationId, int newVisitorId, int newEventId) {
        // Vres tin kratisi me vasi to ID
        Reservation reservationToUpdate = getReservationUsingID(reservationId);

        if (reservationToUpdate == null) {
            return "Reservation with ID " + reservationId + " not found.";
        }

        // Vres ton neo Visitor kai to neo Event
        Visitor newVisitor = visitorServices.getVisitorUsingID(newVisitorId);
        Event newEvent = eventServices.getEventUsingID(newEventId);

        if (newVisitor == null) {
            return "Visitor with ID " + newVisitorId + " not found.";
        }

        if (newEvent == null) {
            return "Event with ID " + newEventId + " not found.";
        }
        // Den eimai sigouri an einai swstos tropos
        // Enimerosi tou Visitor kai tou Event stin kratisi
        Event oldEvent = reservationToUpdate.getEvent(); 
        reservationToUpdate.setVisitor(newVisitor);
        reservationToUpdate.setEvent(newEvent);

        
        return "Reservation with ID " + reservationId + " updated successfully.";
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
    
    // Method which deletes all reservations of one event. Called when Event is Deleted?
    // Den prepei na kanoume alli mia  delete all events of Visitor, otan diagrafetai o Visitor?
    public String cancelAllReservationsForEvent (Integer eventID)
    {
    	Event anEvent = eventServices.getEventUsingID(eventID);
    	 if (anEvent == null) 
    	 {
    		 return "There is no such event!";
    	 }
    	 
    	 List<Reservation> tempReservations = getReservationsByEvent(eventID);
    			 
    	if(tempReservations.isEmpty())
    	{
    		return "This event doesn't have any reservations";

    	}
    				
    	 for (Reservation reservation : tempReservations) {
 	        cancelReservation(reservation.getId());
 	    }		
    				
    	 return "All reservations for this event have been cancelled";
    }
    
  
    
    
    
    /*
     * We want visitors to be able to make reservations to Events 
     * just by giving their ID and the title of the Event. This
     * method checks which eventID corresponds to the given title
     * (by calling the getEventIDFromTitle method).
     * 
     * Then, it uses the "original" addReservation method of this class,
     * so that there are no duplicates
     * 
     * (In case of the event not being found, the Event id will be 0.
     * So when the "original" addReservation checks, it will return
     * an error message) 
     */
    public void addReservation (Integer visitorID, String title)
    {
    	Integer eventID = eventServices.getEventIDFromTitle(title);
    	addReservation(visitorID, eventID);
    	
    }
    
    
    /*
     * Takes the ID of a visitor and  then, using the cancelReservation
     * method, it cancels all the reservations they have made.
     */
    public String cancelAllReservationsForVisitor (Integer visitorID)
    {
    	 List<Reservation> tempReservations = getReservationsByVisitor(visitorID);

    	    if (tempReservations.isEmpty()) {
    	        return "This visitor hasn't made any reservations";
    	    }


    	    for (Reservation reservation : tempReservations) {
    	        cancelReservation(reservation.getId());
    	    }

    	    return "All reservations for this visitor have been cancelled.";
    }
    
}