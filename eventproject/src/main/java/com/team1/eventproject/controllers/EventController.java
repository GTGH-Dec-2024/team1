package com.team1.eventproject.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.services.EventServices;

public class EventController {
	
	@Autowired
	EventServices eventServices;
	
	//Endpoint for GET in /events/all
	@GetMapping("/all")
	public ArrayList<Event> getAllEvents() {
		return eventServices.getAllEvents();
	}
	
	//Endpoint for GET in /events/{id}
	@GetMapping("/{id}")
	public Event getEventUsingID(@PathVariable Integer eventId) {
		return eventServices.getEventUsingID(eventId);
	}
	
	//Endpoing for GET in/events/{id}
	@GetMapping("/{id}")
	public ArrayList<Event> getEventsForOrganizer(Integer organizerId) {
		return eventServices.getEventsForOrganizer(organizerId);
	}
	
	//Endpoint for POST in /events/add
	@PostMapping()
	
	//Endpoint for DELETE in /events/cancel
	//Endpoint for UPDATE in /events/update/{id}
	
	

}