package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Visitor;

@Service
public class VisitorServices {
	private ArrayList<Visitor> allVisitors;
	
	
	public List<Visitor> addVisitor (int id, String name, String surname, String email)
	{
		Visitor temp = new Visitor(id, name, surname, email);
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
