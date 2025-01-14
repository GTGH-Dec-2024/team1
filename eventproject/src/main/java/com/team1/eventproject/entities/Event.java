package com.team1.eventproject.entities;

import org.springframework.stereotype.Service;


public class Event {
	private String title;
	private String theme;
	private String description;
	private String location;
	private Integer maxCapacity;
	private Integer day;
	private Integer month;
	private Integer year;
	private Integer hour;
	private Integer minutes;
	private Integer duration;
	private Organizer organizer;
	private String status; // status = "pending" or "approved" or "not-approved" or "deleted"
	private Integer currentCapacity;
	private Integer id;
	private Integer employeeId;
	private Integer organizerId;

	public Event(Integer organizerId, String title, String theme, String description, String location,
			Integer maxCapacity, Integer day, Integer month, Integer year, Integer hour, Integer minutes,
			Integer duration, Integer id) {

		this.organizerId = organizerId;
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.location = location;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = maxCapacity;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.status = "pending";
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getDate() {
		return String.format("%02d/%02d/%04d", day, month, year);
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

	public String getTime() {
		return String.format("%02d:%02d", hour, minutes);
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Organizer getOrganizer() {
		return organizer;
	}

	public void setOrganizer(Organizer organizer) {
		this.organizer = organizer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCurrentCapacity() {
		return currentCapacity;
	}

	// Checks if the event has space for more reservations
	public boolean hasSpace() {
		return currentCapacity > 0;
	}

	// used when a reservation is made
	public void decreaseCurrentCapacity() {
		currentCapacity--;
	}

	// used when a reservation is cancelled
	public void increaseCurrentCapacity() {
		currentCapacity++;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public void setCurrentCapacity(Integer currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public void setDeletedBy(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getDeletedBy() {
		return employeeId;
	}
	
	public void setOrganizerId(Integer organizerId) {
		this.organizerId = organizerId;
	}

	public Integer getOrganizerId() {
		return organizerId;
	}


	/*
	 * public void showMyVisitors() { ArrayList<Visitor> eventVisitors =
	 * ReservationManager.getInstance().getVisitorsForEvent(this);
	 * 
	 * if (eventVisitors.isEmpty()) {
	 * System.out.println("There are no reservations for the event: " +title); }
	 * else { System.out.println("The visitors for the event " +title+ " are:"); for
	 * (Visitor visitor: eventVisitors) { System.out.println(visitor.getName()+
	 * " "+visitor.getSurname()); } }
	 * 
	 * }
	 * 
	 * 
	 */

	@Override
	public String toString() {
		return "Event [title = " + title + ", Theme = " + theme + ", Description = " + description + ", Location = "
				+ location + ", Available spaces = " + getCurrentCapacity() + "/" + getMaxCapacity() + ", Date = "
				+ getDate() + ", Time = " + getTime() + ", Duration = " + duration + ", Organizer = "
				+ organizer.getName() + " " + organizer.getSurname() + ", Status = " + status + "]";
	}

}
