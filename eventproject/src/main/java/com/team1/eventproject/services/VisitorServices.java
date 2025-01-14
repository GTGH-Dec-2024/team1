package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {
	// The class VisitorServices stores all the visitors in the list allVisitors.
	private ArrayList<Visitor> allVisitors;  
	
	@Autowired
    @Lazy
    private ReservationServices reservationServices;
	
	
	// Constructor
	public VisitorServices(ArrayList<Visitor> allVisitors) {
		this.allVisitors = allVisitors;
	}


	public String addVisitor(String name, String surname, String email)
	{
	    /*
	     * We want all IDs to be given automatically. Therefore, we
	     * use the allVisitors list to help us. If the list is empty,
	     * then we know it is the first object that will be made so its
	     * id will be set to 1.
	     * 
	     * Otherwise, we find the ID of the last object that was added,
	     * and by increasing it by 1 we get the new id!
	     */
	    Integer id; 
	    if(allVisitors.isEmpty()){
	        id = 1;
	    }else{
	        id = allVisitors.get(allVisitors.size() - 1).getId() + 1;
	    }

	    // We create a new Visitor object
	    Visitor temp = new Visitor(name, surname, email, id); 

	    /*
	     * We consider the email to be unique, so we check if the visitor is
	     * already on the list using the email.
	     */
	    for (Visitor visitor : allVisitors) {
	        if (visitor.getEmail().equals(email)) {
	            return "The Visitor: " +name+ " " + surname +" has already been added";
	        }
	    }

	 
	    allVisitors.add(temp); 
	    
	    return "The Visitor " + temp.getName() + " " + temp.getSurname() + " has been added successfully "
	    		+ "and given the ID " + id;
	}
	
	
	// Method which returns visitor based on his id
	public Visitor getVisitorUsingID(Integer id)
	{
		for (Visitor visitor : allVisitors) 
		{
			if (visitor.getId().equals(id)) {  // Changed == to .equals() for Integer comparison
	        
	            return visitor;
	        }
	    }
	    return null;
	    } 
	

	// Method to get all visitors
    public List<Visitor> getAllVisitors() {
        return new ArrayList<>(allVisitors); // Return a copy to ensure immutability
    }

 // Method to update a visitor
    public String updateVisitor(Integer id, String newName, String newSurname, String newEmail) {
    
    	for (Visitor visitor : allVisitors)
        {
        	if (visitor.getId().equals(id)) {
        		if (newName != null)
					visitor.setName(newName);
        		if (newSurname!=null)
        			visitor.setSurname(newSurname);
        		if (newEmail!=null)
        			visitor.setEmail(newEmail);
        		
        	return "The visitor has been updated";
        	}  
        }
    	return "The visitor ID you provided is not correct";
    }
    
    
    /*
     *  Method to delete a visitor. When a visitor gets deleted, all the
     *  reservations they have made get cancelled automatically.
     */
   
    // Method to delete a visitor
    public String deleteVisitor(Integer visitorId) {
        Visitor visitor = getVisitorUsingID(visitorId);
        if (visitor == null || visitor.getStatus().equalsIgnoreCase("deleted")) {
            return "The Visitor with ID " +visitorId+ " doesn't exist or has already been deleted";
        }
        
        visitor.setStatus("deleted"); // Mark as deleted
        reservationServices.cancelAllReservationsForVisitor(visitorId); // Cancel all related reservations
        return "The Visitor with ID " +visitorId+ " has been deleted";
    }
    
}
