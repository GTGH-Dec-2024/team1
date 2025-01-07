/*
 * Contains a list that stores all the reservations,
 * so that we can manage them in a simpler way
 */

import java.util.ArrayList;

public class ReservationManager {
	
		private ArrayList<Reservation> allReservations;
		private static ReservationManager instance;
		  
	
		  
		public ReservationManager() {
			allReservations = new ArrayList<>();
		}
		
	
		
		
		/*
		 * This method is called by a Visitor trying to make
		 * a reservation of an event.
		 * 
		 * First, it checks if the specific event is approved
		 * and if there is space for a reservation. If both of those
		 * are true, it creates the reservation and adds it to the 
		 * reservationManager list
		 * 
		 * Otherwise, it informs the visitor with a message based on
		 * the issue that came up
		 */

		public void createReservation(Event anEvent, Visitor aVisitor)
	    {   	
			//if the event is approved and it is not full, make the reservation
			if (hasReservation(anEvent, aVisitor))
		      {
		    	  System.out.println("You have already made a reservation for this event");
		    	  return;
		      } 
			
			//if the event has not been approved
			if (!anEvent.getStatus().equalsIgnoreCase("approved"))
			{
				System.out.println("The event is not approved!\nYou can't make a reservation!");
				return;
			}
			
			//if there is no space for another reservation
			if (!anEvent.hasSpace())
			{
				System.out.println("You can't make a reservation to this event, the capscity is full");
				return;
		 
			}
			
			allReservations.add(new Reservation (aVisitor, anEvent));
			System.out.println("A reservation for the event: " + anEvent.getTime()+ " has been made for "
								+ aVisitor.getName() + " " + aVisitor.getSurname());
	    }    			
	


		
		public void removeReservation(Event anEvent)
		{
			;
		}
		
		public boolean hasReservation(Event anEvent, Visitor aVisitor)
		{
			for (Reservation reservation : allReservations) {
			      if (reservation.getEvent().equals(anEvent) && reservation.getVisitor().equals(aVisitor))
			      {
			    	  return true;
			      }
		}
			return false;
		}
		
	
		
		public static ReservationManager getInstance() {
		    if (instance == null) {
		       instance = new ReservationManager();
		     }
		   return instance;
		}
		
		
		
	
		
		

}
