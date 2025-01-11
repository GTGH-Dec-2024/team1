package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {
	// The class VisitorServices stores all the visitors in the list allVisitors.
	private ArrayList<Visitor> allVisitors;  
	
	// Method addVisitor adds a new visitor to the allVisitors list
	public List<Visitor> addVisitor (int id, String name, String surname, String email)
	{
		Visitor temp = new Visitor(name, surname, email); // To id den prepei na einai ston constructor giati auto orizetai automata apo tin klasi visitor
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
