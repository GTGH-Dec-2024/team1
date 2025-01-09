package com.team1.eventproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.entities.Organizer;
import com.team1.eventproject.entities.Event;
import com.team1.eventproject.services.ApprovalRequestServices;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		
		ApprovalRequestServices approvalrequestservices = new ApprovalRequestServices();
		
		Employee em1 = new Employee("Maria ", "Papadopoulou", "maria.papadop@gmail.com");
		Organizer o1 = new Organizer("George", "Georgidis","A111","An organizer that likes events");
		Event ev1 = new Event("Cultural Night of Thessaloniki", 
				"Performance",
				" A mesmerizing evening filled with traditional dances, music, and flavors of Macedonia, featuring local dance groups and musical ensembles.", 
				"Aristotelous Square", 25, 15, 6, 2025, 20, 00, 3, o1);
		
		o1.registerRequest(ev1);
		approvalrequestservices.handleRegistrationRequest(ev1, em1, true);
		
	}

}
