package com.team1.eventproject.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.team1.eventproject.services.EventServices;

@Service
public class Organizer extends User {
	private String afm;
	private String description;
	private int id;
	private ArrayList<Event> events;

	public Organizer(String name, String surname, String afm, String description, int id) {
		super(name, surname);
		this.id = id;
		this.afm = afm;
		this.description = description;
		this.events = new ArrayList<Event>();
	}

	public String getAfm() {
		return afm;
	}

	public void setAfm(String afm) {
		this.afm = afm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Organizer [afm=" + afm + ", description=" + description + ", events=" + events + "]";
	}

}
