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


	// Method addVisitor adds a new visitor to the allVisitors list
	public List<Visitor> addVisitor (String name, String surname, String email)
	{
		/*
		 * We want all IDs to be given automatically. Therefore, we
		 * use the allVisitors list to help us. If the list is empty,
		 * then we know it is the first object that will be made so its
		 * id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added,
		 * and by increasing it by 1 we get the new id!
		 * 
		 */
		int id; 
		if(allVisitors.isEmpty()){
			id = 1;
			
		}else
		{
			id = allVisitors.get(allVisitors.size() - 1).getId() + 1;
		}
		
		
		Visitor temp = new Visitor(name, surname, email, id); // To id den prepei na einai ston constructor giati auto orizetai automata apo tin klasi visitor
		if (!allVisitors.contains(temp))
		{
			allVisitors.add(temp);
		}
		else
		{
			System.out.println("The Visitor: " +temp.getName() +temp.getSurname()+
					" has already been added");
		}
		return allVisitors;
	}
	
	
	// Method which returns visitor based on his id
	public Visitor getVisitorUsingID(Integer id)
	{
		for (Visitor visitor : allVisitors) 
		{
	        if (visitor.getId() == id) 
	        {
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
    public boolean updateVisitor(int id, String newName, String newSurname, String newEmail) {
        Visitor visitor = getVisitorUsingID(id);
        if (visitor != null) {
            visitor.setName(newName);
            visitor.setSurname(newSurname);
            visitor.setEmail(newEmail);
            return true; // Update successful
        }
        return false; // Visitor not found
    }

    /*
     *  Method to delete a visitor. When a visitor gets deleted, all the
     *  reservations they have made get cancelled automatically.
     */
   
    public String deleteVisitor(Integer visitorId) {
    	Visitor temp = getVisitorUsingID(visitorId);
		
    	if (temp == null || temp.getStatus().equalsIgnoreCase("deleted"))
			return "Visitor not found or has already been deleted";
	
		temp.setStatus("deleted");
		
		String message = reservationServices.cancelAllReservationsForVisitor(visitorId);
		return "Visitor " + temp.getName() +" has been deleted. "+ message;
        
    }
}
