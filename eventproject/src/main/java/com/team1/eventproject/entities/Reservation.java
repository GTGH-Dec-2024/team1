package com.team1.eventproject.entities;
/* The class Reservation represents one reservation that one visitor has made for an Event */

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	    // The visitor who has made the reservation
		private final Visitor visitor;  // connection with the class Visitor 
	    // The event to which the reservation has been made
		private final Event event;  // connection with the class Event
	    // visitor and event must be final so that they can not change for the specific reservation????
		
		private int id;
		
	    /* Constructor.
	     * visitor the visitor who makes the reservation to the event.
	     * event the event for which the reservation is being made. */	     
	    public Reservation(Visitor visitor, Event event, int id) {
	    	this.id = id;
	    	this.visitor = visitor;
	        this.event = event;
	    }

	  
	    // Getters and Setters for the fields Visitor and Reservation
	    public Visitor getVisitor() {
	        return visitor;
	    }


	    public Event getEvent() {
	        return event;
	    }


		public int getId() {
			return id;
		}


		public void setVisitor(Visitor newVisitor) {
			// TODO Auto-generated method stub
			
		}


		public void setEvent(Event newEvent) {
			// TODO Auto-generated method stub
			
		}
	    
		@Override
		public String toString() {
		    return "Reservation [id=" + id + ", visitor=" + visitor + ", event=" + event + "]";
		}
	    // Prepei na exei toString h visitor kai h event!
	   
	           

}
