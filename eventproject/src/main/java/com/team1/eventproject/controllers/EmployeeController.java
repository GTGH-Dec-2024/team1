package com.team1.eventproject.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.Employee;
import com.team1.eventproject.services.EmployeeServices;


@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	EmployeeServices employeeservices;
	
	@PostMapping("/add")
	public String addEmployee(@RequestParam String name, @RequestParam String surname,@RequestParam String email) {
		return employeeservices.addEmployee(name, surname, email);
	}
	
	@DeleteMapping("/delete")
	public String deleteEmployee (@RequestParam Integer id) {
		return employeeservices.deleteEmployee(id);
	}
	
	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees(){
		return employeeservices.getAllEmployees();
	}
	
	@GetMapping("/activeEmployees")
	public List<Employee> getAllActiveEmployees(){
		return employeeservices.getAllActiveEmployees();
	}
	


}
