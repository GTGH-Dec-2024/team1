/*
 * This class will help in searching if there is
 * an ApprovalRequest for a specific Event
 * 
 * It is used to store all the approval requests
 * 
 * When an ApprovalRequest is made, it is added to the list
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApprovalRequestManager {
	
	private ArrayList<ApprovalRequest> allApprovalRequests = new ArrayList<>();

	
	
	
	public void createApprovalRequest(Event anEvent, String type, Organizer submittedBy, String comments)
		{
			ApprovalRequest aRequest = new ApprovalRequest(type, LocalDateTime.now(), 
					submittedBy, anEvent, "Creating a request to "+type+" the event");
			allApprovalRequests.add(aRequest);
			
			System.out.println("You have made a request to " +type+
					"the event "+anEvent.getTitle() + ".");	
		}

        
//		
//	NOMIZW PREPEI NA MPEI STHN EVENT!!
//	
//    public ApprovalRequest findApprovalRequest(Event anEvent) 
//    {
//        for (ApprovalRequest i : allApprovalRequests) {
//            if (i.getAnEvent().equals(anEvent)) {
//                return i;
//            }
//        }
//        return null;
//    }
	

}
