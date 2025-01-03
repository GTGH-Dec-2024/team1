import java.util.ArrayList;
import java.util.List;
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

       
    }
}

       
	}


