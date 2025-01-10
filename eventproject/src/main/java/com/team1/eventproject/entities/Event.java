package com.team1.eventproject.entities;

public class Event {
	private String title;
	private String theme;
	private String description;
	private String location;
	private int maxCapacity;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int duration;
	private Organizer organizer;
	private String status; // status = "pending" or "approved" or "not-approved" or "deleted"
	private int currentCapacity;
	private int id;
	
	
	public Event(int id,String title, String theme, String description, String location, int maxCapacity, int day, int month,
			int year, int hour, int minutes, int duration, Organizer organizer) {
		this.id = id;
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.location = location;
		this.maxCapacity = maxCapacity;
		// this.currentCapacity = maxCapacity;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.organizer = organizer;
		this.status = "pending";

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

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDate() {
		return String.format("%02d/%02d/%04d", day, month, year);
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getTime() {
		return String.format("%02d:%02d", hour, minutes);
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
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

	public int getCurrentCapacity() {
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
	 * 
	 * @Override public String toString() { return "Event [title = " + title +
	 * ", Theme = " + theme + ", Description = " + description + ", Location = " +
	 * location + ", Available spaces = " +
	 * getCurrentCapacity()+"/"+getMaxCapacity() + ", Date = " + getDate() +
	 * ", Time = " + getTime() + ", Duration = " + duration + ", Organizer = " +
	 * organizer.getName() + " " + organizer.getSurname() + ", Status = " + status +
	 * ", Reservations = " +
	 * ReservationManager.getInstance().getVisitorsForEvent(this) + "]"; }
	 */
}
