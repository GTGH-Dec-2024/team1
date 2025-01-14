package com.team1.eventproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.services.OrganizerServices;

@RestController
@RequestMapping("organizers")
public class OrganizerController {
	@Autowired
	OrganizerServices organizerServices;
	
	@PostMapping("/add")
	public String addOrganizer (@RequestParam String name, @RequestParam String surname, @RequestParam String afm, @RequestParam String description){
		return organizerServices.addOrganizer(name, surname, afm, description);
	}
	
	@GetMapping("/organizerById")
	public Organizer getOrganizerUsingID(@RequestParam Integer id){
		return organizerServices.getOrganizerUsingID(id);
	}
	
	@DeleteMapping("/delete")
	public String deleteOrganizer(@RequestParam Integer organizerId) {
		return organizerServices.deleteOrganizer(organizerId);
	}
	
	@GetMapping("/allOrganizers")
	public ArrayList<Organizer> getAllOrganizers() {
		return organizerServices.getAllOrganizers();
	}
	
	@GetMapping("/activeOrganizes")
	public List<Organizer> getAllActiveOrganizers(){
		return organizerServices.getAllActiveOrganizers();
	}
	
}
