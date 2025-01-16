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
	public String addEvent(@RequestParam Integer organizerId, @RequestParam String title, @RequestParam String theme,
			@RequestParam String description, @RequestParam String location, @RequestParam Integer maxCapacity,
			@RequestParam Integer day, @RequestParam Integer month, @RequestParam Integer year,
			@RequestParam Integer hour, @RequestParam Integer minutes, @RequestParam Integer duration,
			@RequestParam String comments) {
		return eventServices.addEvent(organizerId, title, theme, description, location, maxCapacity, day, month, year,
				hour, minutes, duration, comments);
	}

	@DeleteMapping("/delete")
	public String deleteEvent(@RequestParam Integer id) {
		return eventServices.deleteEvent(id);
	}

	@PutMapping("/updateEvent")
	public String updateEvent(Integer eventId, Integer organizerId, String title, String theme, String description,
			String location, Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour,
			Integer minutes, Integer duration, Integer id) {
		return eventServices.updateEvent(eventId, organizerId, title, theme, description, location, maxCapacity, day,
				month, year, hour, minutes, duration, id);
	}

	@GetMapping("/all")
	public ArrayList<Event> getAllEvents() {
		return eventServices.getAllEvents();
	}

	@GetMapping("/eventUsingId")
	public Event getEventUsingID(@RequestParam Integer id) {
		return eventServices.getEventUsingID(id);
	}

	@GetMapping("/eventsForOrganizerId")
	public ArrayList<Event> getEventsForOrganizer(@RequestParam Integer id) {
		return eventServices.getEventsForOrganizer(id);
	}

	@GetMapping("/searchEvents")
	public List<Event> searchEvents(@RequestParam Integer id, @RequestParam Integer day, @RequestParam Integer month,
			@RequestParam Integer year, @RequestParam String location, @RequestParam String theme) {
		return eventServices.searchEvents(id, day, month, year, location, theme);
	}

	@GetMapping("/upcomingEventsGivenOrganizerId")
	public ArrayList<Event> getUpcomingEventsPerOrganizer(@RequestParam Integer id) {
		return eventServices.getUpcomingEventsPerOrganizer(id);
	}
	
	

}