package com.team1.eventproject.services;
/* H klasi Reservation Services tha xrisimopoieitai san pinakas-vasi dedomenwn opou tha apothikeuontai 
 * oles oi kratiseis pou exoun kanei oi visitors gia ta events pou theloun na parakolouthisoun.
 */

public class ReservationServices {
	
	private ArrayList<Reservation> allReservations;
	private static ReservationManager instance;
	  

	// Constructor
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
    // Einai i methodos pou tha metaferthei apo ton Visitor
	public void createReservation(Event anEvent, Visitor aVisitor)
    {   	
		//if there is a reservation for this event already
		if (findReservation(anEvent, aVisitor) != null)
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
			System.out.println("You can't make a reservation to this event, the capacity is full");
			return;
	 
		}
		
		//makes the reservation, adds it to the
		//list of reservations and decreases the capacity of the event
		allReservations.add(new Reservation (aVisitor, anEvent));
		anEvent.decreaseCurrentCapacity();
		
		
		System.out.println("A reservation for the event " + anEvent.getTitle()+ " has been made for "
							+ aVisitor.getName() + " " + aVisitor.getSurname());
    }    			



	// Tha ginei to idio me panw
	public void removeReservation(Event anEvent, Visitor aVisitor)
	{
		Reservation temp = findReservation(anEvent, aVisitor);
		
		if (temp != null)
	      {
			//removes the reservation from the list,
			//increases the capacity of the event
			allReservations.remove(temp);
			anEvent.increaseCurrentCapacity();
	      } 
		else
			System.out.println("You haven't made a reservation for this event");
		
	}
	
	
	public ArrayList<Reservation> getAllReservations() {
		return allReservations;
	}




	/*
	 * This method helps create and delete a reservation.
	 * 
	 * It searches within the allReservations ArrayList and if
	 * a reservation with the given attributes exists then it
	 * returns the reservation, otherwise it returns null
	 * 
	 * Based on what it returns, the createReservation and
	 * deleteReservation classes act accordingly
	 * 
	 */
	// Tha anazitoume Reservation me vasi to event kai ton visitor
	public Reservation findReservation(Event anEvent, Visitor aVisitor)
	{
		for (Reservation reservation : allReservations) {
		      if (reservation.getEvent().equals(anEvent) && reservation.getVisitor().equals(aVisitor))
		      {
		    	  return reservation;
		      }
	}
		return null;
	}
	

	
	
	/*
	 * Checks the allReservations ArrayList and returns all
	 * the events in which a specific visitor has made a reservation
	 * 
	*/
	// Methodos pou tha epistrefei ola ta event sta opoia exei kanei reservation enas visitor
	public ArrayList<Event> getEventsForVisitor(Visitor aVisitor) {
	   
		ArrayList<Event> visitorEvents = new ArrayList<>();
	    
	    for (Reservation reservation : allReservations) 
	    {
	        if (reservation.getVisitor().equals(aVisitor)) {
	        	visitorEvents.add(reservation.getEvent());
	        }
	    }
	   
	   return visitorEvents;
	    
	    
	}

	/*
	 * Checks the allReservations ArrayList and returns all
	 * the Visitors that have made a reservation for a specific Event.
	 * 
	*/
	// Methodos pou tha epistrefei olous tous visitors pou exoun kanei reservation gia sugkekrimeno event
	public ArrayList<Visitor> getVisitorsForEvent(Event anEvent) {
	   
		ArrayList<Visitor> eventVisitors = new ArrayList<>();
	    
	    for (Reservation reservation : allReservations) {
	       
	    	if (reservation.getEvent().equals(anEvent)) 
	        {
	            eventVisitors.add(reservation.getVisitor());
	        }
	    }
	    
	    return eventVisitors;
	}
 
	// Ti kanei auto???
	public static ReservationManager getInstance() {
	    if (instance == null) {
	       instance = new ReservationManager();
	     }
	   return instance;
	}
	
	

}
