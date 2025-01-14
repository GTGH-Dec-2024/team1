package com.team1.eventproject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.entities.Employee;

@Service
public class EmployeeServices {
		
	@Autowired
	ApprovalRequestServices approvalRequestServices; 
	
	@Autowired
	EventServices eventServices;
	
	
	
	private ArrayList<Employee> allEmployees;
		
		
		
		public EmployeeServices(ArrayList<Employee> allEmployees) {
			this.allEmployees = new ArrayList<>();
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
	
		
		public String acceptRequest(Integer requestID, Integer employeeID) {

			ValidService validationResult = this.isValid(requestID, employeeID);

			if (!validationResult.isValid()) {
				return validationResult.getMessage();
			}

			ApprovalRequest tempRequest = validationResult.getRequest();

			if (tempRequest.getType().equalsIgnoreCase("register")) 
			{
				eventServices.updateEvent(tempRequest.getEventID(), "approved");
			}

			else {
				eventServices.deleteEvent(tempRequest.getEventID());
			}

			tempRequest.setIsApproved(true); // used to return a list of approved requests
			approvalRequestServices.closeRequest(requestID, employeeID, "The request has been accepted");

			return "The employee with ID " + employeeID + " has just ACCEPTED to "
			+ tempRequest.getType() + " the event with ID: " + tempRequest.getEventID() + "\n";

		}
		
		
		/*
		 * First, we check if the request is valid.
		 * 
		 * If it is a request to register an Event, we set its status to "not-approved".
		 * 
		 * If the request is to delete an Event, when the employee denies it the Event
		 * status remains the same
		 * 
		 * Afterwards, the request is updated to "closed", and we add the employee who
		 * handled it. A message is printed.
		 * 
		 */
		public String denyRequest(Integer requestID, Integer employeeID) {

			ValidService validationResult = this.isValid(requestID, employeeID);

			if (!validationResult.isValid()) {
				return validationResult.getMessage();
			}

			ApprovalRequest tempRequest = validationResult.getRequest();
			if (tempRequest.getType().equalsIgnoreCase("register")) {
				eventServices.updateEvent(tempRequest.getEventID(), "not-approved");

			}
			// no change when the request type is "delete"


			approvalRequestServices.closeRequest(requestID, employeeID, "The request has been denied");

			return "The employee with ID " + employeeID + " has just DENIED to "
			+ tempRequest.getType() + " the event with ID: " + tempRequest.getEventID() + "\n";

		}


		/*
		 * Checks if the request can be processed.
		 * 
		 * * Both the ApprovalRequest and the Employee objects must be non-null, and the Request
		 * status must not be "closed". If any of these conditions fail, the method
		 * returns a ValidService object with the appropriate error message.
		 * 
		 * This method was made to avoid duplicate code in acceptRequest and
		 * deleteReques. The ValidService class was made to help.
		 * 
		 */
		
		private ValidService isValid(Integer requestID, Integer employeeID) {
			
			if (getEmployeeUsingID(employeeID) == null) {
				return new ValidService(null, false, "There is no employee with this id! The request can't be handled");
			}

			ApprovalRequest tempRequest = approvalRequestServices.getApprovalRequestUsingID(requestID);

			if (tempRequest == null) {
				return new ValidService(null, false, "The request ID is invalid");
			}

			if (tempRequest.getStatus().equalsIgnoreCase("closed")) {
				return new ValidService(tempRequest, false,
						"The request with ID: " + requestID + " has already been handled.");
			}

			return new ValidService(tempRequest, true, "Request is valid");
		}
		
		 /*
	     * Updates an employee's details.
	     * Returns a success or error message.
	     */
	    public String updateEmployee(Integer id, String newName, String newSurname, String newEmail) {
	        Employee employee = getEmployeeUsingID(id);

	        if (employee == null) {
	            return "Employee with ID " + id + " not found.";
	        }

	        if (employee.getStatus().equalsIgnoreCase("deleted")) {
	            return "Cannot update a deleted employee.";
	        }

	        // Update employee details
	        employee.setName(newName);
	        employee.setSurname(newSurname);
	        employee.setEmail(newEmail);

	        return "Employee with ID " + id + " updated successfully.";
	    }
	}
		


