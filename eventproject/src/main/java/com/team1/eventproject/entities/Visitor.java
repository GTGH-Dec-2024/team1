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
    


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
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



	// toString method
	@Override
	public String toString() {
	    return "Visitor [id=" + id + ", name=" + this.getName() + ", surname=" + this.getSurname() + ", email=" + email + "]";
	}


	

}
