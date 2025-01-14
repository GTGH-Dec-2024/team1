package com.team1.eventproject.entities;
/* The class Reservation represents one reservation that one visitor has made for an Event */

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	 private Integer id;          // ID της κράτησης
	    private Integer visitorId;   // ID του επισκέπτη
	    private Integer eventId;     // ID της εκδήλωσης

	    // Constructor που δέχεται visitorId και eventId
	    public Reservation(Integer visitorId, Integer eventId, Integer id) {
	        this.visitorId = visitorId;
	        this.eventId = eventId;
	        this.id = id;
	    }

	  
	 // Getters και setters
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
