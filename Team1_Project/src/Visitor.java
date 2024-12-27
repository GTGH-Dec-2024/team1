/*
 * 
 */
public class Visitor extends User {
	private String email;

	public Visitor(String name, String surname, String email) {
		super(name, surname);
		this.email = email;
	}
	
	public void makeReservation (Event anEvent)
	{
		/*
		 * prospathei na valei ton visitor sto event
		 * Stelnei sthn addReservation to onoma tou kai 
		 * to event sto opoio thelei na mpei
		 * 
		 */
	}
	
	public void cancelReservation (Event anEvent)
	{
		/*
		 * kalei thn removeReservation tis klasis Event,
		 * stelnei to onoma tou kai to event apo to
		 * opoio thelei na bgei
		 * 
		 */
	}
	
	
	/*
	 * CLASS POU KANEI SEARCH THN EVENT LIST
	 */
	


}
