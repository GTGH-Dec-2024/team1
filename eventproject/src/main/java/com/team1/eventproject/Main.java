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
		
	}

}
