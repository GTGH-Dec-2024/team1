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
		
		public void createReservation(Event anEvent, Visitor aVisitor)
	    {   	
			for (Reservation reservation : allReservations) {
			      if (reservation.getEvent().equals(anEvent) && reservation.getVisitor().equals(aVisitor))
			      {
			    	  System.out.println("You have already made a reservation for this event");
			    	  return;
			      }
				
			}
			     
			  
			allReservations.add(new Reservation (aVisitor, anEvent));
			    			
		}
		
		
		
		public static ReservationManager getInstance() {
		    if (instance == null) {
		       instance = new ReservationManager();
		     }
		   return instance;
		}
		
		
		
	
		
		

}
