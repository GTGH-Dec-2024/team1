package com.team1.eventproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.team1.eventproject.services.EventServices;

public class EventController {
	
	@Autowired
	EventServices eventServises;
	
	public String addEvent(Integer eventId, Integer organizerId, String title, String theme, String description,
			String location, Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour,
			Integer minutes, Integer duration, String comments)

}
