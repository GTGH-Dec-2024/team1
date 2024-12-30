/*
 * This class will help in searching if there is
 * an ApprovalRequest for a specific Event
 * 
 * It is used to store all the approval requests
 * 
 * When an ApprovalRequest is made, it is added to the list
 * 
 * 
 * Isws to kathe request na exei ena id??
 */

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ApprovalRequestManager {
	
	private ArrayList<ApprovalRequest> allApprovalRequests;

	
	
	public ApprovalRequestManager(ArrayList<ApprovalRequest> allApprovalRequests) 
	{
		this.allApprovalRequests = new ArrayList<>();
	}



	public void createApprovalRequest(Event anEvent, String type, Organizer submittedBy, String comments)
		{
			ApprovalRequest aRequest = new ApprovalRequest(type, LocalDateTime.now(), 
					submittedBy, anEvent, "Creating a request to "+type+" the event");
			allApprovalRequests.add(aRequest);
			
			System.out.println("You have made a request to " +type+
					"the event "+anEvent.getTitle() + ".");	
		}

        
/*		
	NOMIZW PREPEI NA MPEI STHN EVENT??
	TI GINETAI AN EXW 2 REQUEST GIA ENA EVENT? STHN ARXH GIA
	CREATION, META GIA DELETION
				(LOGIKA APLA ELEGXO KAI TO TYPE)
	
    public ApprovalRequest findApprovalRequest(Event anEvent) 
    {
        for (ApprovalRequest i : allApprovalRequests) {
            if (i.getAnEvent().equals(anEvent)) {
                return i;
            }
        }
        return null;
    }
*/	

}
