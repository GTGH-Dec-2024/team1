/*
 * To arrayList reservations na einai edw h na kanw alli
 * klasi ReservationList kai auth na diaxeirizetai ta 
 * reservations??
 * 
 */


import java.util.ArrayList;

public class Event {
	private String title;
	private String theme;
	private String description;
	private String location;
	private String status; 
	/*
	 * ???fantazomai created/pending/approved/not-approved/cancelled ????
	 * Na rwthsoume :'(
	 * ??????????
	 */
	
	private Organizer theOrganizer;
	
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int duration;
	private int maxCapacity;
	private int currentCapacity;
	
	private ArrayList<Reservation> reservations;
	/*
	 * lista me ola ta reservations gia to paron Event,
	 * wste na epistrepsw tous participants (kapou tous zitaei)
	 * 
	 */
	
	//CONSTRUCTOR, edw kanw kai co current capacity =max
	//An katalaba kala ta events ta ftiaxnw sth MAIN
	
	 public void addReservation (Reservation aReservation)
		{
			/*
			 * Tsekarei an to event einai approved.
			 * Meta an einai ok ta capacity klp.
			 * Enimeronei to currentCapacity
			 * Ftiaxnei antikeimeno typou Reservation kai to vazei
			 * sthn arrayList me ta reservations
			 *	
			 * Emfanizei an mporese na mpei h oxi
			 * 
			 * Enallaktika mporoyme na tin kanoyme boolean, na epistrefei
			 * an mporese na mpei 
			 * 
			 */
		}
		
		public void removeReservation (Reservation aReservation)
		{
			/*
			 * An o visitor einai ontws sto paron Event
			 * (an yparxei sto ArrayList me ta reservations dhladh)
			 * ton bgazo KAI megalwnw to currentCapacity
			 * 
			 * Emfanizw an ola kala h oxi
			 * 
			 */
		}
		
		
		public ArrayList<Reservation> getReservations() 
		{
			return reservations;
		}

		public void setStatus()
		{
			/*
			 * ti xristimopoiei h ApprovalRequest
			 * gia na enimerwsei an to status einai approved??
			 */
		}
		
	/*	public boolean isApproved()
		{
			/*
			 * epistrefei true/false
			 * analoga me to status tou Event
			 * 
			 * Mallon den xreiazetai
			 */
		//}
		
		private String toString()
		{
			/*
			 * override ths toString gia omorfi ektypwsi
			 * Isws ti xrisimopoiw gia tis anafores
			 * pou tha vlepei o upallilos
			 */
			
		}
	
	
	

}
