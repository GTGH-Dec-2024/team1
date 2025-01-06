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
		
		public void addReservation(Reservation aReservation)
	    {   	
			allReservations.add(aReservation);
		}
		
		
		
		public static ReservationManager getInstance() {
		   \ if (instance == null) {
		       instance = new ReservationManager();
		     }
		   return instance;
		
		
		
	
		
		

}
