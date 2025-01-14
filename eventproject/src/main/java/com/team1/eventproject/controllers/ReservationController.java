/*
 * package com.team1.eventproject.controllers;
 * 
 * import com.team1.eventproject.entities.Reservation; import
 * com.team1.eventproject.services.ReservationServices; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import java.util.List;
 * 
 * @RestController // Declares that this class is a REST controller.
 * 
 * @RequestMapping("reservations") // Base URL for all reservation-related
 * endpoints. public class ReservationController {
 * 
 * @Autowired private ReservationServices reservationServices; // Injects the
 * ReservationServices service
 * 
 * 
 * Get all reservations. - HTTP Method: GET - URL: /reservations/all -
 * Description: Fetches all reservations.
 * 
 * @GetMapping("/all") public List<Reservation> getAllReservations() { return
 * reservationServices.getAllReservations(); // Return all reservations. }
 * 
 * 
 * Add a new reservation. - HTTP Method: POST - URL: /reservations/add - Request
 * Parameters: visitorId, eventId - Description: Adds a new reservation based on
 * visitor ID and event ID.
 * 
 * @PostMapping("/add") public String addReservation(@RequestParam(required =
 * false) Integer visitorId, @RequestParam(required = false) Integer eventId) {
 * return reservationServices.addReservation(visitorId, eventId); // Adds
 * reservation using visitor and event IDs. }
 * 
 * 
 * Cancel a specific reservation. - HTTP Method: DELETE - URL:
 * /reservations/cancel - Request Parameter: reservationId - Description:
 * Cancels a specific reservation using its ID.
 * 
 * @DeleteMapping("/cancel") public String
 * cancelReservation(@RequestParam(required = false) Integer reservationId) {
 * return reservationServices.cancelReservation(reservationId); // Cancels
 * reservation by ID. }
 * 
 * 
 * Update a reservation. - HTTP Method: PUT - URL: /reservations/update -
 * Request Parameters: id, newVisitorId, newEventId - Description: Updates an
 * existing reservation with new visitor and event IDs.
 * 
 * @PutMapping("/update") public String updateReservation(
 * 
 * @RequestParam(required = false) Integer id, // ID of the reservation to
 * update.
 * 
 * @RequestParam(required = false) Integer newVisitorId, // New visitor ID.
 * 
 * @RequestParam(required = false) Integer newEventId) { // New event ID. return
 * reservationServices.updateReservation(id, newVisitorId, newEventId); //
 * Updates reservation. }
 * 
 * 
 * Get reservations by visitor ID. - HTTP Method: GET - URL:
 * /reservations/visitor - Request Parameter: visitorId - Description: Fetches
 * all reservations made by a specific visitor.
 * 
 * @GetMapping("/visitor") public List<Reservation>
 * getReservationsByVisitor(@RequestParam(required = false) Integer visitorId) {
 * // If visitorId is not provided, it will be considered as null. if (visitorId
 * == null) { return reservationServices.getAllReservations(); // If visitorId
 * is not provided, fetch all reservations. } return
 * reservationServices.getReservationsByVisitor(visitorId); // Get reservations
 * by visitor ID. }
 * 
 * 
 * Get reservations by event ID. - HTTP Method: GET - URL: /reservations/event -
 * Request Parameter: eventId - Description: Fetches all reservations for a
 * specific event.
 * 
 * @GetMapping("/event") public List<Reservation>
 * getReservationsByEvent(@RequestParam(required = false) Integer eventId) { //
 * If eventId is not provided, it will be considered as null. if (eventId ==
 * null) { return reservationServices.getAllReservations(); // If eventId is not
 * provided, fetch all reservations. } return
 * reservationServices.getReservationsByEvent(eventId); // Get reservations by
 * event ID. }
 * 
 * 
 * Count reservations for a specific event. - HTTP Method: GET - URL:
 * /reservations/event/count - Request Parameter: eventId - Description: Returns
 * the total number of reservations for a specific event.
 * 
 * @GetMapping("/event/count") public int
 * countReservationsForEvent(@RequestParam(required = false) Integer eventId) {
 * // If eventId is not provided, return 0. if (eventId == null) { return 0; //
 * No eventId provided, so no reservations to count. } return
 * reservationServices.countReservationsForEvent(eventId); // Get the count of
 * reservations for the event. }
 * 
 * 
 * Get a reservation by its ID. - HTTP Method: GET - URL: /reservations -
 * Request Parameter: id - Description: Fetches a reservation by its ID.
 * 
 * @GetMapping("/{id}") public Reservation
 * getReservationById(@RequestParam(required = false) Integer id) { // If id is
 * not provided, return null or appropriate message. if (id == null) { return
 * null; // If id is not provided, return null. } return
 * reservationServices.getReservationUsingID(id); // Get reservation details
 * using the ID. }
 * 
 * 
 * Cancel all reservations for a specific event. - HTTP Method: DELETE - URL:
 * /reservations/delete/event - Request Parameter: eventId - Description:
 * Cancels all reservations related to a specific event.
 * 
 * @DeleteMapping("/delete/event") public String
 * cancelAllReservationsOfEvent(@RequestParam(required = false) Integer eventId)
 * { // If eventId is not provided, return a message or handle it accordingly.
 * if (eventId == null) { return "No eventId provided to cancel reservations.";
 * // If no eventId is provided. } return
 * reservationServices.cancelAllReservationsForEvent(eventId); // Cancel all
 * reservations for the event. }
 * 
 * 
 * Add a reservation using visitor ID and event title. - HTTP Method: POST -
 * URL: /reservations/add - Request Parameters: visitorID, title - Description:
 * Adds a reservation based on visitor ID and event title.
 * 
 * @PostMapping("/add") public String addReservation(@RequestParam(required =
 * false) Integer visitorID, @RequestParam(required = false) String title) {
 * reservationServices.addReservation(visitorID, title); // Adds reservation
 * using visitor ID and event title. return "Reservation added successfully!";
 * // Return success message after reservation is added. }
 * 
 * 
 * Cancel all reservations for a specific visitor. - HTTP Method: DELETE - URL:
 * /reservations/cancel/all - Request Parameter: visitorID - Description:
 * Cancels all reservations made by a specific visitor.
 * 
 * @DeleteMapping("/cancel/all") public String
 * cancelAllReservationsForVisitor(@RequestParam(required = false) Integer
 * visitorID) { // If visitorID is not provided, return a message or handle it
 * accordingly. if (visitorID == null) { return
 * "No visitorID provided to cancel all reservations."; // If no visitorID is
 * provided. } reservationServices.cancelAllReservationsForVisitor(visitorID);
 * // Cancel all reservations for the visitor. return
 * "All reservations canceled successfully."; // Return success message. } }
 */