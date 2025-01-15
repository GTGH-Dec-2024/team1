package com.team1.eventproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.Event;
import com.team1.eventproject.services.EventServices;

@RestController
@RequestMapping("events")
public class EventController {

	@Autowired
	EventServices eventServices;
	
	@PostMapping("/add")
	public String addEvent(@RequestParam Integer organizerId, @RequestParam String title,
			@RequestParam String theme, @RequestParam String description, @RequestParam String location,
			@RequestParam Integer maxCapacity, @RequestParam Integer day, @RequestParam Integer month,
			@RequestParam Integer year, @RequestParam Integer hour, @RequestParam Integer minutes,
			@RequestParam Integer duration, @RequestParam String comments) {
		return eventServices.addEvent(organizerId, title, theme, description, location, maxCapacity, day,
				month, year, hour, minutes, duration, comments);
	}

	@DeleteMapping("/delete")
	public String deleteEvent(@RequestParam Integer eventId) {
		return eventServices.deleteEvent(eventId);
	}
	
	@PutMapping("/update")
	public String updateEvent(@RequestParam Integer eventId, @RequestParam(required=false)  Integer organizerId,@RequestParam(required=false) String title,@RequestParam(required=false) String theme,
			@RequestParam(required=false)String description, @RequestParam(required=false) String location,@RequestParam(required=false) Integer maxCapacity,
			@RequestParam(required=false) Integer day,@RequestParam(required=false) Integer month,@RequestParam(required=false) Integer year,@RequestParam(required=false) Integer hour,
			@RequestParam(required=false) Integer minutes,@RequestParam(required=false) Integer duration, @RequestParam(required=false) String comments) {
		return eventServices.updateEvent(eventId, organizerId, title, theme, description, location,maxCapacity, 
				day, month, year, hour, minutes, duration, comments);
	}


	@GetMapping("/all")
	public ArrayList<Event> getAllEvents() {
		return eventServices.getAllEvents();
	}

	@GetMapping("/eventsUsingId")
	public Event getEventUsingID(@RequestParam Integer eventId) {
		return eventServices.getEventUsingID(eventId);
	}

	@GetMapping("/getEventsForOrganizerId")
	public ArrayList<Event> getEventsForOrganizer(@RequestParam Integer organizerId) {
		return eventServices.getEventsForOrganizer(organizerId);
	}

	@GetMapping("/searchEvents")
	public List<Event> searchEvents(@RequestParam Integer id, @RequestParam(required=false) Integer day, @RequestParam(required=false) Integer month,
			@RequestParam(required=false) Integer year, @RequestParam(required=false) String location, @RequestParam(required=false) String theme) {
		return eventServices.searchEvents(id, day, month, year, location, theme);
	}

	@GetMapping("/upcomingEventsGivenOrganizerId")
	public ArrayList<Event> getUpcomingEventsPerOrganizer(@RequestParam Integer organizerId) {
		return eventServices.getUpcomingEventsPerOrganizer(organizerId);
	}
	
	@DeleteMapping("/cancelAllEventsForOrganizer")
	public String cancelAllEventsForOrganizer (@RequestParam Integer organizerId) {
		return eventServices.cancelAllEventsForOrganizer(organizerId);
	}
	
	@GetMapping ("/eventIDFromTitle")
	public Integer getEventIDFromTitle(@RequestParam String title)
	{
		return eventServices.getEventIDFromTitle(title);
	}
}