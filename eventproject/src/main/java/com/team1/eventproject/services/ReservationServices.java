package com.team1.eventproject.services;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.FileOutputStream;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;

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
        // Otan prosthetoume kratisi gia ton Visitor prepei to currentCapacity tou antistoixou Events na ginetai -1
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
         
        // Enimerosi xoritikotitas tou Event
        // Einai swstos o katw tropos gia thn afxisi tis xoritikothtas?
        // eventServices.updateEventCapacity(event.getId(), event.getCurrentCapacity() + 1);  

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
        Event oldEvent = reservationToUpdate.getEvent(); // Gia enimerosi tis xoritikotitas
        reservationToUpdate.setVisitor(newVisitor);
        reservationToUpdate.setEvent(newEvent);

        // Enimerosi xoritikotitas
        //eventServices.updateEventCapacity(oldEvent.getId(), oldEvent.getCurrentCapacity() + 1); // Auxisi xoritikotitas tou paliou event
        //eventServices.updateEventCapacity(newEvent.getId(), newEvent.getCurrentCapacity() - 1); // Meiosi xoritikotitas tou neou event

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
    public String cancelAllReservationsofEvent (int eventID)
    {
    	Event anEvent = eventServices.getEventUsingID(eventID);
    	 if (anEvent == null) 
    	 {
    		 return "The id provided doesn't match to any event";
    	 }
    	 
    	 for (Reservation res : reservations)
    	 {
    		 if (res.getEvent().equals(anEvent))
    		 {
    			 this.cancelReservation(res.getId());
    		 }
    	 }
    	 return "All reservations for the event: " +anEvent.getTitle()
    	 	+ " have been cancelled";
    }
    
    
    public String exportReservationsToPdf(String filepath) {
    	String message;
    	
    	try{
    		
    		PdfWriter writer = new PdfWriter(filepath);
    		PdfDocument pdfDocument = new PdfDocument(writer);
    		Document document = new Document(pdfDocument);
    		
    		document.add(new Paragraph("Event Reservations").setBold().setFontSize(16));
    		
    		float[] columnWidths= {1,3,3};
    		Table table = new Table(columnWidths);
    		
    		table.addHeaderCell("RESERVATION ID");
    		table.addHeaderCell("VISITOR NAME");
    		table.addHeaderCell("EVENT TITLE");
    		
    		for(Reservation reservation : reservations) {
    			table.addCell(String.valueOf(reservation.getId()));
    			table.addCell(String.valueOf(reservation.getVisitor().getName()));
    			table.addCell(String.valueOf(reservation.getEvent().getTitle()));
    		}
    		
    		document.add(table);
    		document.close();
    		
    		message="Reservations for each event exported succesfully to "+filepath;
    	}catch(Exception e) {
    		e.printStackTrace();
    		message = "Error exporting reservations to PDF: "+e.getMessage();
    	}
    	
    	return message;
    } 
}