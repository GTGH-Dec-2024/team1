package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Employee;

@Service
public class EmployeeServices {
		private ArrayList<Employee> allEmployees;
		
		public List<Employee> addEmployee (int id, String name, String surname, String email)
		{
			Employee temp = new Employee(id, name, surname, email);
			if (!allEmployees.contains(temp))
			{
				allEmployees.add(temp);
			}
			else
			{
				System.out.println("The employee: " +temp.getName()+ temp.getSurname()+
						" has already been added");
			}
			return allEmployees;
		}
		
		
		
		public Employee getEmployeeByID(int id)
		{
			for (Employee employee : allEmployees) 
			{
		        if (employee.getId() == id) 
		        {
		            return employee;
		        }
		    }
		    return null; 
		}
		
		

}
