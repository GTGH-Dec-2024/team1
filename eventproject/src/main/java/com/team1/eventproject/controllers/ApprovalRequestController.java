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
	
//	@PostMapping("/add")
//	public String addApprovalRequest(@RequestParam String type,@RequestParam Integer OrganizerID,@RequestParam Integer eventID, @RequestParam(required = false) String comments)
//	{
//		return approvalrequestservices.addApprovalRequest(type, OrganizerID, eventID, comments);
//	}
	
	@GetMapping("/pendingRequests")
	public List<ApprovalRequest> getPendingRequests()
	{
		return approvalrequestservices.getPendingRequests();
	}
	
	@GetMapping("/approvedRequests")
	public List<ApprovalRequest> getApprovedRequests()
	{
		return approvalrequestservices.getApprovedRequests();
	}
	
	@GetMapping("/requestsForEvent")
	public List<ApprovalRequest> getRequestsForEvent(@RequestParam Integer eventID)
	{
		return approvalrequestservices.getRequestsForEvent(eventID);
	}
	
	
	
	


}
