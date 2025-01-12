package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {
	// The class VisitorServices stores all the visitors in the list allVisitors.
	private ArrayList<Visitor> allVisitors;  
	
	
	
	public VisitorServices(ArrayList<Visitor> allVisitors) {
		this.allVisitors = allVisitors;
	}


	// Method addVisitor adds a new visitor to the allVisitors list
	public List<Visitor> addVisitor (String name, String surname, String email)
	{
		/*
		 * We want all IDs to be given automatically. Therefore, we
		 * use the allEmployees list to help us. If the list is empty,
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
	public Visitor getVisitorUsingID(int id)
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

}
