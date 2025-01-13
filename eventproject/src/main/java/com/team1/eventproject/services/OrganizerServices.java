package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Visitor;


@Service
public class OrganizerServices {
	private ArrayList<Organizer> allOrganizers;
	
	 @Autowired
	    private EventServices eventServices;
	 
	 
	 
	 
	public OrganizerServices(ArrayList<Organizer> allOrganizers) {
		this.allOrganizers = new ArrayList<>();
	}



	public List<Organizer> addOrganizer (String name, String surname, String afm, String description)
	{
		
		/*
		 * We want all IDs to be given automatically. Therefore, we
		 * use the allOrganizers list to help us. If the list is empty,
		 * then we know it is the first object that will be made so its
		 * id will be set to 1.
		 * 
		 * Otherwise, we find the ID of the last object that was added,
		 * and by increasing it by 1 we get the new id!
		 * 
		 */
		int id; 
		if(allOrganizers.isEmpty()){
			id = 1;
			
		}else
		{
			id = allOrganizers.get(allOrganizers.size() - 1).getId() + 1;
		}
		
		
		Organizer temp = new Organizer(name, surname, afm, description,id);
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
	
	
	/*
	 * When an organizer gets deleted, their status becomes "deleted".
	 * All of their upcoming Events also get deleted.
	 * 
	 */
	  public String deleteOrganizer(Integer organizerId) {
	    	Organizer temp = getOrganizerUsingID(organizerId);
			
	    	if (temp == null || temp.getStatus().equalsIgnoreCase("deleted"))
				return "Organizer not found or has already been deleted";
		
			temp.setStatus("deleted");
			
			String message = eventServices.cancelAllEventsForOrganizer(organizerId);
			return "Organizer " + temp.getName() +" has been deleted. "+ message;
	        
	    }
	
	

	
}
