package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Organizer;


@Service
public class OrganizerServices {
	private ArrayList<Organizer> allOrganizers;
	
	
	public List<Organizer> addOrganizer (int id, String name, String surname, String afm, String description)
	{
		Organizer temp = new Organizer(id, name, surname, afm, description);
		if (!allOrganizers.contains(temp))
		{
			allOrganizers.add(temp);
		}
		else
		{
			System.out.println("The Organizer: " +temp.getName() +temp.getSurname()+
					" has already been added");
		}
		return allOrganizers;
	}
	
	
	
	public Organizer getOrganizerUsingID(int id)
	{
		for (Organizer organizer : allOrganizers) 
		{
	        if (organizer.getId() == id) 
	        {
	            return organizer;
	        }
	    }
	    return null; 
	}

}
