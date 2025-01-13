package com.team1.eventproject.entities;
/* The class Visitor represents one visitor who attends an event.
 * 1)The visitor can search the events that he would like to attend based on specific
 * criteria such as: The date, the location , the theme of the event (mipos kai kati allo?)
 * 2)After that, he can do a reservation to the Event that he wants to attend.
 * 3)If he changes his mind, he can cancel the reservation that he has done.
 */


/* The class Visitor inherits the abstract class User and he has also an email. */
public class Visitor extends User {
	private String email;
	private String status;
	private Integer id;  // Changed primitive int to Integer
    
	// Constructor
    public Visitor(String name, String surname, String email, Integer id) {
        super(name, surname); // Calls the constructor of the superclass User 
        this.id = id;
        this.status = "active";
        this.email = email;
    }
    
    // GETTERS FOR ALL FIELDS
	public Integer getId() {
		
		return id;
	} 

	public String getEmail() 
	{
		return email;
	}
    
		
	public String getStatus() {
		return status;
	}

	// SETTERS FOR THE FIELDS STATUS AND EMAIL (to id den exei setter gt den mporoume na to allaxoume-ginetai mono automata?)
	public void setStatus(String status) {
		this.status = status;
	}

	public void setEmail(String newEmail) {
				
	}
	
	
	// toString method
	@Override
	public String toString() {
	    return "Visitor [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}


	

}
