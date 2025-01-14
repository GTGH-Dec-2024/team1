package com.team1.eventproject.entities;

public class Reservation {
    // The IDs for the visitor and event
    private Integer visitorId;  // Connection to the Visitor ID
    private Integer eventId;    // Connection to the Event ID
    private Integer id;         // Reservation ID
    
    /* Constructor.
     * visitorId the ID of the visitor who makes the reservation to the event.
     * eventId the ID of the event for which the reservation is being made. 
     * id the unique ID of the reservation.
     */
    public Reservation(Integer visitorId, Integer eventId, Integer id) {
        this.visitorId = visitorId;
        this.eventId = eventId;
        this.id = id;
    }

    // GETTERS FOR ALL FIELDS
    public Integer getVisitorId() {
        return visitorId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public Integer getId() {
        return id;
    }

    // SETTERS FOR visitorId AND eventId with null checks
    public void setVisitorId(Integer newVisitorId) {
        if (newVisitorId != null) { // Added null check
            this.visitorId = newVisitorId;
        } else {
            System.out.println("Visitor ID cannot be null.");
        }
    }

    public void setEventId(Integer newEventId) {
        if (newEventId != null) { // Added null check
            this.eventId = newEventId;
        } else {
            System.out.println("Event ID cannot be null.");
        }
    }

    // SETTER FOR id
    public void setId(Integer newId) {
        if (newId != null) { // Added null check
            this.id = newId;
        } else {
            System.out.println("ID cannot be null.");
        }
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", visitorId=" + visitorId + ", eventId=" + eventId + "]";
    }
}
