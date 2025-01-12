/**
 * This class helps in handling approvalRequests related to events.
 * 
 * Since both acceptRequest and rejectRequest have to make the same
 * checks to ensure the parameters are not null, we use a ValidService
 * object to inform the addApprovalRequest method about the validation
 * of the request
 * 
 * 
 * @Param request is the ApprovalRequest we want to check
 * @Param is Valid is boolean, based on if the request passed the
 * validation checks
 * @Param message; if the request is not valid, it explains why
 *
 */

package com.team1.eventproject.services;

import com.team1.eventproject.entities.ApprovalRequest;

public class ValidService {
	

    private ApprovalRequest request;
	private boolean isValid;
    private String message;


    public ValidService(ApprovalRequest request, boolean isValid, String message) {
		this.request = request;
		this.isValid = isValid;
		this.message = message;
	}

    public ApprovalRequest getRequest() {
        return request;
    }
    
    public boolean isValid() {
        return isValid;
    }

    public String getMessage() {
        return message;
    }



}
