
/*
 * PROSEXW: O Employee mporei na diagrapsei monos tou Events!
 * Den mporei na ftiajei!
 * 
 * An o organizer ftiajei/diagrapsei Event omws, o Employee
 * prepei na dwsei to ok
 * 
 * 
 * NA ELEGXEI AN TO MAIL EINAI VALID??
 * 
 */

public class Employee extends User{
	private String email;

	public Employee(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
	}
	
	
	public void approveEvent(/*Event anEvent?? ApprovalRequest request??*/)
	{
		/*
		 * An uparxei approvalRequest kane set to status tis se approved
		 * to idio kai sto status tis Event
		 * 
		 * Kalese th registerEvent tis EventManager gia na kaneis
		 * to neo Event
		 * 
		 * An den uparxei approvalRequest den mporeis na kaneis tipota
		 */
	}
	
	public void deleteEvent(/*Event anEvent?? ApprovalRequest request??*/)
	{
		/*
		 * Edw den xreiazetai na uparxei request.
		 * 
		 * An uparxei to kaneis approved klp klp,
		 * alla eite uparxei eite oxi sigoura
		 * diagrafeis apo ti lista to Event tis EventManager
		 * 
		 * 
		 */
	}
	/*
	 * ISWS approveEvent kai deleteEvent na enwthoun????
	 * kai na ginoun px approveRequest??
	 * to vlepoyme
	 */
	
	 public void rejectRequest(ApprovalRequest request) 
	 {
		 /*
		  * An  kanw reject prepei na exw auth th methodo
		  * pou tha enimerwnei oti to request einai closed
		  * 
		  */
	 }
	

	
}
