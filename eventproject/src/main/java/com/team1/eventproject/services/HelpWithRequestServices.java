package com.team1.eventproject.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team1.eventproject.entities.ApprovalRequest;



@Service
public class HelpWithRequestServices {
	private ArrayList<ApprovalRequest> allPending;
	
	@Autowired
	ApprovalRequestServices approvalRequestServices;

	public HelpWithRequestServices(ArrayList<ApprovalRequest> allPending) {
		this.allPending = new ArrayList<>();
	}
	
	public void addApprovalRequest(String type, Integer organizerID, Integer eventID, String comments)
	{
		approvalRequestServices.addApprovalRequest(type, organizerID, eventID, comments);
	}
	

	
	
	
}
