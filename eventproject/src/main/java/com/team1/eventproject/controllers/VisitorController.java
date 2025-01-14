package com.team1.eventproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.Visitor;
import com.team1.eventproject.services.VisitorServices;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    @Autowired 
    VisitorServices visitorServices;
    
    @PostMapping("/add")
	public String addVisitor (@RequestParam String name, @RequestParam String surname, @RequestParam String email){
		return visitorServices.addVisitor(name, surname, email);
	}
    
    @DeleteMapping("/delete")
 	public String deleteVisitor (@RequestParam Integer id){
 		return visitorServices.deleteVisitor(id);
 	}
     
    

    

}