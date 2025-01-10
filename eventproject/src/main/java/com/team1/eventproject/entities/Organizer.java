package com.team1.eventproject.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.team1.eventproject.services.EventServices;

@Service
public class Organizer extends User {
	private String afm;
	private String description;
	private ArrayList<Event> events;

	public Organizer(int id, String name, String surname, String afm, String description) {
		super(name, surname, id);
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

	@Override
	public String toString() {
		return "Organizer [afm=" + afm + ", description=" + description + ", events=" + events + "]";
	}

}
