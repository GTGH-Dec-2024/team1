package com.team1.eventproject.entities;
/* The class Reservation represents one reservation that one visitor has made for an Event */

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	    // The visitor who has made the reservation
		private Visitor visitor;  // connection with the class Visitor 
	    // The event to which the reservation has been made
		private Event event;  // connection with the class Event
	    // visitor and event must be final so that they can not change for the specific reservation????
		
		private Integer id; // Changed to Integer for null safety
		
	    /* Constructor.
	     * visitor the visitor who makes the reservation to the event.
	     * event the event for which the reservation is being made. */	     
	    public Reservation(Visitor visitor, Event event, Integer id) {
	    	this.id = id;
	    	this.visitor = visitor;
	        this.event = event;
	    }

	  
	    // GETTERS FOR ALL FIELDS
	    public Visitor getVisitor() {
	        return visitor;
	    }


	    public Event getEvent() {
	        return event;
	    }


		public Integer getId() {
			return id;
		}

        // SETTERS FOR VISITOR AND EVENT with null checks (einai aparaithtoi edw?)
		public void setVisitor(Visitor newVisitor) {
	        if (newVisitor != null) { // Added null check 
	            this.visitor = newVisitor;
	        } else {
	            System.out.println("Visitor cannot be null."); // Added message for null case 
	        }
	    }


		public void setEvent(Event newEvent) {
	        if (newEvent != null) { // Added null check 
	            this.event = newEvent;
	        } else {
	            System.out.println("Event cannot be null."); // Added message for null case 
	        }
	    }
		
		// BORW NA PEIRAZW ME SETTER to id? Prepei na ginetai?
		public void setId(Integer newId) { // id changed to Integer 
	        if (newId != null) { // Added null check 
	            this.id = newId;
	        } else {
	            System.out.println("ID cannot be null."); // Added message for null case 
	        }
	    }
	    
		@Override
		public String toString() {
		    return "Reservation [id=" + id + ", visitor=" + visitor + ", event=" + event + "]";
		}
	    // Prepei na exei toString h visitor kai h event!
	   
	           

}
