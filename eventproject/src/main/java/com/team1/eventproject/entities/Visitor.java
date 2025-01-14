package com.team1.eventproject.entities;

public class Visitor extends User {
	private String email;
	private String status;
	private Integer id; 
    
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

	
	
	
	public void setEmail(String email) {
		this.email = email;
	}

	// toString method
	@Override
	public String toString() {
	    return "Visitor [id=" + id + ", name=" + name + ", surname=" + surname + ", email=" + email + "]";
	}


	

}
