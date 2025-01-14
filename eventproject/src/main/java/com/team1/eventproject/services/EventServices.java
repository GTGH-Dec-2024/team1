package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Event;

@Service
public class EventServices {

    // List that stores all events
    private List<Event> events;

    @Autowired
    private ReservationManagerServices reservationManagerServices; // Dependency for ReservationManagerServices

    // Constructor to initialize the events list
    public EventServices() {
        this.events = new ArrayList<>();
    }

    /*
     * Adds a new event.
     * @param title The title of the event.
     * @param description The description of the event.
     * @param capacity The capacity of the event.
     * @return A message indicating the result of the operation.
     */
    public String addEvent(String title, String description, Integer capacity) {
        Integer id = events.isEmpty() ? 1 : events.get(events.size() - 1).getId() + 1;

        Event event = new Event(id, title, description, description, description, capacity, id, id, id, id, id, id, id);
        events.add(event);

        return "Event created successfully: " + event.getTitle();
    }

    /*
     * Retrieves an event by its ID.
     * @param id The ID of the event.
     * @return The event object or null if not found.
     */
    public Event getEventUsingID(Integer id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                return event;
            }
        }
        return null;
    }

    /*
     * Decreases the event's capacity after a reservation is made.
     * @param eventId The ID of the event.
     * @return A message indicating the result of the operation.
     */
    public String decreaseCurrentCapacity(Integer eventId) {
        Event event = getEventUsingID(eventId);
        if (event != null && event.getCurrentCapacity() > 0) {
            event.setCurrentCapacity(event.getCurrentCapacity() - 1);
            return "Event capacity decreased successfully for event: " + event.getTitle();
        }
        return "Event with ID " + eventId + " not found or no available capacity.";
    }

    /*
     * Increases the event's capacity when a reservation is cancelled.
     * @param eventId The ID of the event.
     * @return A message indicating the result of the operation.
     */
    public String increaseCurrentCapacity(Integer eventId) {
        Event event = getEventUsingID(eventId);
        if (event != null) {
            event.setCurrentCapacity(event.getCurrentCapacity() + 1);
            return "Event capacity increased successfully for event: " + event.getTitle();
        }
        return "Event with ID " + eventId + " not found.";
    }

    /*
     * Gets all events.
     * @return A list of all events.
     */
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
}
