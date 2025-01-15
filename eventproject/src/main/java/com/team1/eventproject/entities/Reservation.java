package com.team1.eventproject.entities;
/* The class Reservation represents one reservation that one visitor has made for an Event */


public class Reservation {
	 private Integer id;          // id of the reservation
	    private Integer visitorId;   // id of the visitor
	    private Integer eventId;     // id of the event

	    // Constructor
	    public Reservation(Integer visitorId, Integer eventId, Integer id) {
	        this.visitorId = visitorId;
	        this.eventId = eventId;
	        this.id = id;
	    }

	  
	 // Getters and Setters
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public Integer getVisitorId() {
	        return visitorId;
	    }

	    public void setVisitorId(Integer visitorId) {
	        this.visitorId = visitorId;
	    }

	    public Integer getEventId() {
	        return eventId;
	    }

	    public void setEventId(Integer eventId) {
	        this.eventId = eventId;
	    }
	    
	    @Override
	    public String toString() {
	        return "Reservation{" +
	               "id=" + id +
	               ", visitorId=" + visitorId +
	               ", eventId=" + eventId +
	               '}';
	    }
	           

}
