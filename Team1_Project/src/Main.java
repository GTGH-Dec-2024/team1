
public class Main {

	public static void main(String[] args) {

		Employee em1 = new Employee("Maria ", "Papadopoulou", "maria.papadop@gmail.com");
		Employee em2 = new Employee("Dimitris ", "Konstantinidis", "dimitris.kons@gmail.com");
		Employee em3 = new Employee("Giorgos  ", "Nikolaidis", " giorgos.nikolaid@example.com");

		
		Organizer o1 = new Organizer("George", "Georgidis","A111","An organizer that likes events");
		Organizer o2 = new Organizer("Maria", "Maraki","A222"," ");
		Organizer o3 = new Organizer("Petros", "Petridis","A333","The best organizer");
		Organizer o4 = new Organizer("Zoe", "Zoedi", "A444", "A new event organizer!");
	
		Event ev1 = new Event("Cultural Night of Thessaloniki", 
				"Traditional Music and Dances of Macedonia",
				" A mesmerizing evening filled with traditional dances, music, and flavors of Macedonia, featuring local dance groups and musical ensembles.", 
				"Aristotelous Square", 150, 15, 6, 2025, 8, 00, 3, o1);
		
		Event ev2 = new Event("Thessaloniki Taste Festival", 
				"Showcasing Local Gastronomy with Restaurants and Producers", 
				"A journey into the flavors of Thessaloniki, with exhibitions of local products, cooking demonstrations, and tastings by renowned chefs.",
				"Nikis Avenue Pedestrian Zone", 200, 10, 9, 2025, 6, 00, 4, o2);
		
		
		
		

	}

}
