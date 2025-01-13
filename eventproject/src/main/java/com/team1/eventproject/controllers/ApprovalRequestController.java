package com.team1.eventproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team1.eventproject.entities.ApprovalRequest;
import com.team1.eventproject.services.ApprovalRequestServices;

@RestController
@RequestMapping("requests")
public class ApprovalRequestController {

	@Autowired 
	ApprovalRequestServices approvalrequestservices;
	
	@PostMapping("/add")
	public String addApprovalRequest(@RequestParam String type,@RequestParam Integer OrganizerID,@RequestParam Integer eventID, @RequestParam(required = false) String comments)
	{
		return approvalrequestservices.addApprovalRequest(type, OrganizerID, eventID, comments);
	}
	
	@PostMapping("/accept")
	public String acceptRequest(@RequestParam Integer requestID, @RequestParam Integer employeeID)
	{
		return approvalrequestservices.acceptRequest(requestID, employeeID);
	}
	
	@PostMapping("/deny")
	public String denyRequest(@RequestParam Integer requestID, @RequestParam Integer employeeID)
	{
		return approvalrequestservices.denyRequest(requestID, employeeID);
	}
	
	@GetMapping("/pendingRequests")
	public List<ApprovalRequest> getPendingRequests()
	{
		return approvalrequestservices.getPendingRequests();
	}
	
	


}
