import java.util.ArrayList;
import java.util.List;
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
				"A city-wide marathon event that welcomes runners of all levels to explore Thessaloniki s landmarks while promoting a healthy and active lifestyle. Includes live music and food stands.",
				"Start and finish at White Tower Square", 15, 20, 10, 2025, 21, 30, 6, o1);
		
		Event ev7 = new Event("Open-Air Cinema Night", 
				"Classic Movies Under the Stars", 
				"A relaxing evening of cinema in an open-air setup, featuring classic movies on a giant screen. Bring your blanket and enjoy popcorn under the night sky.", 
				"Seikh Sou Forest Park", 20, 12, 7, 2025, 20, 30, 3, o2);
		
		Event ev8 = new Event("Thessaloniki Tech Fair", 
				"Innovations Shaping the Future", 
				"An exhibition of cutting-edge technologies and startups, with hands-on demonstrations, networking opportunities, and guest speakers from the tech world.", 
				"Thessaloniki International Fairgrounds (TIF HELEXPO)", 25, 15, 11, 2025, 15 ,30, 8, o3);

		
        List<Visitor> visitors = new ArrayList<>();

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
        
        
        //Organizers are creating approval requests for their events
        ApprovalRequest registerRequest1 =  o3.registerRequest(ev3);      
        ApprovalRequest registerRequest2 = o2.registerRequest(ev2);
        
       //this employee approves the request, 
        //making the status of the event "approved" 
        em3.approveRequest(registerRequest1);
        
        //the same employee rejects the other request,
        //making the status of the event "not-approved"
        em3.rejectRequest(registerRequest2);
        
        
        //An organizer makes a request to delete an event, 
        //and the employee accepts it
        ApprovalRequest removalRequest1 =  o1.removalRequest(ev6);
        em1.approveRequest(removalRequest1);
        
        //When an employee deletes an event, there is no need
        //for approval from anyone
        em2.deleteEvent(ev8);
        
    //    visitor1.searchEvent(12, 7, 2025, "Thessaloniki International Fairgrounds (TIF HELEXPO)", "Thessaloniki Tech Fair");
    //   System.out.println(EventManager.getInstance().getEvents());
       
    }
}

       