package com.team1.eventproject.entities;
/* The class Visitor represents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */

import java.util.ArrayList;

/* The class Visitor inherits the abstract class User and he has also an email. */
public class Visitor extends User {
	private String email;
	private int id;
    
	// Constructor
    public Visitor(String name, String surname, String email, int id) {
        super(name, surname); // Calls the constructor of the superclass User 
        this.id = id;
        this.email = email;
    }
    

	public int getId() {
		
		return id;
	}
 

	public String getEmail() 
	{
		return email;
	}

	@Override
	public String toString()
	{
		return name + surname;
	}




}
