package com.team1.eventproject.services;


import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.entities.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class VisitorServices {
	private List<Event> events; // List with all events
    private List<Reservation> reservations; // List with all Reservations

    // Constructor
    public VisitorService(List<Event> events) {
        this.events = events;
        this.reservations = new ArrayList<>();
    }

    
}
