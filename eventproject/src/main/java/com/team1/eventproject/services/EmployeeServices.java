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
		


		public String addEmployee (String name, String surname, String email)
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
			Integer id; 
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
				return("The employee: " +temp.getName()+ temp.getSurname()+
						" has already been added");
			}
			return "The employee " +name + " " + surname +" has been added successfully,"
					+ "with id: "+ id+ "!";
		}
		
		
		
		public String deleteEmployee(Integer id) {
			Employee temp = getEmployeeUsingID(id);
			if (temp == null || temp.getStatus().equalsIgnoreCase("deleted"))
				return "Employee not found, or has already been deleted";
			
			temp.setStatus("deleted");
			return "Employee " + temp.getName() + " " + temp.getSurname() +" has been deleted.";
		}



		public Employee getEmployeeUsingID(Integer id)
		{
			for (Employee employee : allEmployees) 
			{
		        if (employee.getId().equals(id)) 
		        {
		            return employee;
		        }
		    }
		    return null; 
		}
		
		
		public List<Employee> getAllEmployees() {
			return allEmployees;
		}
		
		
		/*
		 * The employee list contains all the employees, even the 
		 * ones that have been deleted (status.equalsIgnoreCase("deleted")
		 * The following method returns a list with the employees that
		 * are active
		 * 
		 */
		public List<Employee> getAllActiveEmployees() {

		    List<Employee> activeEmployees = new ArrayList<>();

		    for (Employee em : allEmployees) {
		        if (em.getStatus().equalsIgnoreCase("active")) {
		            activeEmployees.add(em);
		        }
		    }

		    return activeEmployees;
		}
	
		
		

}
