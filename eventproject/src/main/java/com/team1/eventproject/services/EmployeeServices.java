package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.Employee;

@Service
public class EmployeeServices {
		private ArrayList<Employee> allEmployees;
		
		
		
		public EmployeeServices(ArrayList<Employee> allEmployees) {
			this.allEmployees = allEmployees;
		}
		


		public List<Employee> addEmployee (String name, String surname, String email)
		{
			
			/*
			 * We want all IDs to be given automatically. Therefore, we
			 * use the allEmployees list to help us. If the list is empty,
			 * then we know it is the first object that will be made so its
			 * id will be set to 1.
			 * 
			 * Otherwise, we find the ID of the last object that was added,
			 * and by increasing it by 1 we get the new id!
			 * 
			 */
			int id; 
			if(allEmployees.isEmpty()){
				id = 1;
				
			}else
			{
				id = allEmployees.get(allEmployees.size() - 1).getId() + 1;
			}
	
			
			Employee temp = new Employee(email, name, surname, id);
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
		
		
		
		public String removeEmployee(int id) {
			Employee temp = getEmployeeUsingID(id);
			allEmployees.remove(temp);
			return "Employee " + temp.getName() +"has been removed.";
		}



		public Employee getEmployeeUsingID(int id)
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
