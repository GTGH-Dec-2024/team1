
public class Main {
	public static void main(String[] args) {
		// Dimiourgia 10 Visitors
		
        // Lista episkephtwn
        List<Visitor> visitors = new ArrayList<>();

        // Dimiourgia episkephtwn
        Visitor visitor1 = new Visitor("Giorgos", "Papadopoulos", "visitor1@gmail.com");
        visitors.add(visitor1);

        Visitor visitor2 = new Visitor("Maria", "Ioannidou", "visitor2@gmail.com");
        visitors.add(visitor2);

        Visitor visitor3 = new Visitor("Kostas", "Alexiou", "visitor3@gmail.com" );
        visitors.add(visitor3);

        Visitor visitor4 = new Visitor("Panagiotis", "Konstantinidis", "visitor4@gmail.com");
        visitors.add(visitor4);

        Visitor visitor5 = new Visitor("Katerina", "Antoniou","visitor5@gmail.com");
        visitors.add(visitor5);

        Visitor visitor6 = new Visitor("Nikos", "Papakonstantinou","visitor6@gmail.com" );
        visitors.add(visitor6);

        Visitor visitor7 = new Visitor("Maria", "Oikonomidou", "visitor7@gmail.com");
        visitors.add(visitor7);

        Visitor visitor8 = new Visitor("Nikos", "Pantelidis","visitor8@gmail.com" );
        visitors.add(visitor8);

        Visitor visitor9 = new Visitor("Anastasia", "Georgiou","visitor9@gmail.com" );
        visitors.add(visitor9);

        Visitor visitor10 = new Visitor("Dimitra", "Aggelidou", "visitor10@gmail.com");
        visitors.add(visitor10);

       
   
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
				"Aristotelous Square", 25, 15, 6, 2025, 20, 00, 3, o1);
		
		Event ev2 = new Event("Thessaloniki Taste Festival", 
				"Showcasing Local Gastronomy with Restaurants and Producers", 
				"A journey into the flavors of Thessaloniki, with exhibitions of local products, cooking demonstrations, and tastings by renowned chefs.",
				"Nikis Avenue Pedestrian Zone", 30, 10, 9, 2025, 18, 00, 4, o2);
		
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
		
		Event ev6 = new Event("Thessaloniki Marathon Festival",
				"Running Through the History and Beauty of Thessaloniki",
				"A city-wide marathon event that welcomes runners of all levels to explore Thessaloniki’s landmarks while promoting a healthy and active lifestyle. Includes live music and food stands.",
				"Start and finish at White Tower Square", 15, 20, 10, 2025, 21, 30, 6, o1);
		
		Event ev7 = new Event("Open-Air Cinema Night", 
				"Classic Movies Under the Stars", 
				"A relaxing evening of cinema in an open-air setup, featuring classic movies on a giant screen. Bring your blanket and enjoy popcorn under the night sky.", 
				"Seikh Sou Forest Park", 20, 12, 7, 2025, 20, 30, 3, o2);
		
		Event ev8 = new Event("Thessaloniki Tech Fair", 
				"Innovations Shaping the Future", 
				"An exhibition of cutting-edge technologies and startups, with hands-on demonstrations, networking opportunities, and guest speakers from the tech world.", 
				"Thessaloniki International Fairgrounds (TIF HELEXPO)", 25, 15, 11, 2025, 15 ,30, 8, o2);

		
		// Egrafi parakolouthisis endilwshs toulaxiston se mia ekdilosi gia olous tous episkeptes
		// Set status of two events as "approved".
        ev1.setStatus("approved");
        ev2.setStatus("approved");
        // Reservation in at least one event for each Visitor
        for (Visitor visitor : visitors) {
            if (ev1.getCurrentCapacity() < ev1.getMaxCapacity()) {
                visitor.makeReservation(ev1);
            } else if (ev2.getCurrentCapacity() < ev2.getMaxCapacity()) {
                visitor.makeReservation(ev2);
            } else {
                System.out.println(visitor.getName() + " " + visitor.getSurname() + " could not register for any events.");
            }
        }
        // Print Reservations
        System.out.println("\nReservations for Event 1:");
        System.out.println(ev1.getReservations());

        System.out.println("\nReservations for Event 2:");
        System.out.println(ev2.getReservations());
		
		
        /* Test what will happen in case that one visitor tries to register to an event
         * which doesn't have any open seats left.
         * We will use the event 4 for the test.
         */
       // Set the status of event 4 as "approved"
        ev4.setStatus("approved");
        
        
       // The two first visitors successfully register to the event.
        visitor1.makeReservation(ev4); // visitor 1 makes reservation for the event 4
        visitor2.makeReservation(ev4);
        
      // The third visitor tries to make a reservation but there aren't any seats left.
        System.out.println("\nTrying to make a reservation for visitor3:");
        visitor3.makeReservation(ev4);
        
      // Print reservations for the event 4.
        System.out.println("\nReservations for the event:");
        System.out.println(ev4.getReservations());
        
     // We will try to cancel reservations for the event 4     
        visitor1.cancelReservation(ev4);  // visitor 1 cancels reservation for the event 4
        visitor2.cancelReservation(ev4);  
     // Final reservations for the event 4
        System.out.println("\nReservations for the event " + ev4.getTitle() + ": " + ev4.getReservations());
        
        
	}

       
	}


