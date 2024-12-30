 /* To arrayList reservations na einai edw h na kanw alli
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
	private int maxCapacity;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int duration;
	private Organizer organizer;
	private String status; 
	private int currentCapacity;
	
	/*
	 * ???fantazomai created/pending/approved/not-approved/cancelled ????
	 * Na rwthsoume :'(
	 * ??????????
	 */
	
	public Event(String title, String theme, String description, String location,int maxCapacity, int day, int month, int year, int hour, int minutes, int duration, Organizer organizer) {
		this.title = title;
		this.theme = theme;
		this.description = description;
		this.location = location;
		this.maxCapacity = maxCapacity;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.organizer = organizer;
		this.status = "Pending";
	}
	
	
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
		
		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTheme() {
			return theme;
		}

		public void setTheme(String theme) {
			this.theme = theme;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getMaxCapacity() {
			return maxCapacity;
		}

		public void setMaxCapacity(int maxCapacity) {
			this.maxCapacity = maxCapacity;
		}

		public int getDay() {
			return day;
		}

		public void setDay(int day) {
			this.day = day;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getHour() {
			return hour;
		}

		public void setHour(int hour) {
			this.hour = hour;
		}

		public int getMinutes() {
			return minutes;
		}

		public void setMinutes(int minutes) {
			this.minutes = minutes;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public Organizer getOrgnzr() {
			return organizer;
		}

		public void setOrgnzr(Organizer orgnzr) {
			this.organizer = orgnzr;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public int getCurrentCapacity() {
			return currentCapacity;
		}

		public void setCurrentCapacity(int currentCapacity) {
			this.currentCapacity = currentCapacity;
		}

		public void setReservations(ArrayList<Reservation> reservations) {
			this.reservations = reservations;
		}

		@Override
		public String toString() {
			return "Event [title=" + title + ", theme=" + theme + ", description=" + description + ", location="
					+ location + ", maxCapacity=" + maxCapacity + ", day=" + day + ", month=" + month + ", year=" + year
					+ ", hour=" + hour + ", minutes=" + minutes + ", duration=" + duration + ", organizer=" + organizer
					+ ", status=" + status + ", currentCapacity=" + currentCapacity + ", reservations=" + reservations
					+ "]";
		}
	
	
	

}
