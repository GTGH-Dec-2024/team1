package com.team1.eventproject.entities;


public class Organizer extends User {
	private String afm;
	private String description;
	private Integer id;
	private String status;


	public Organizer(String name, String surname, String afm, String description, Integer id) {
		super(name, surname);
		this.id = id;
		this.afm = afm;
		this.status = "active";
		this.description = description;
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
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Organizer [afm=" + afm + ", description=" + description + "]";
	}

	
	
}
