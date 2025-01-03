
public class Main {

	public static void main(String[] args) {
		
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
		
		Event ev3 = new Event("Ice skating theater", 
				"Dance/Theater/Performance", 
				"The best dancers from all over the world meet for one huge performance.",
				"Venizelou 20", 10, 17, 3, 2025, 20, 00, 2, o3);
		
		Event ev4 = new Event("The most magical show", 
				"Exclusive magic show", 
				"A famous magician will shock you with her tricks",
				"Vasilisis Olgas 104", 5, 30, 5, 2025, 21, 00, 2, o4);
		
		Event ev5 = new Event("The three best friends", 
				"A theater for kids of all ages", 
				"This play is full of messages about the value of friendship!",
				"Egnatias 45", 20, 9, 8, 2025, 18, 00, 2, o4);
		
		
		Employee em1 = new Employee("Maria ", "Papadopoulou", "maria.papadop@gmail.com");
		Employee em2 = new Employee("Dimitris ", "Konstantinidis", "dimitris.kons@gmail.com");
		Employee em3 = new Employee("Giorgos  ", "Nikolaidis", " giorgos.nikolaid@example.com");
		
		
		
		

	}

}
