import java.util.ArrayList;
import java.util.List;
public class Main {
	public static void main(String[] args) {
		// Dimiourgia 10 Visitor
		// Dimiourgia ellinikon onomaton kai eponimon
        String[] FirstNames = {"Giorgos", "Maria", "Kostas", "Eleni", "Panagiotis", "Anastasia", "Nikos", "Dimitra", "Stavros", "Katerina"};
        String[] LastNames = {"Papadopoulos", "Ioannidis", "Papakonstantinou", "Alexiou", "Konstantinidis", "Antoniou", "Michailidis", "Oikonomou", "Lampropoulos", "Christodoulou"};

        // Lista gia na kratithoun oi episkephtes
        List<Visitor> visitors = new ArrayList<>();

        
        // Dimiourgia kai ektypwsi 10 episkephtwn
        for (int i = 0; i < 10; i++) {
            String name = FirstNames[i % FirstNames.length];
            String surname = LastNames[i % LastNames.length];
            String email = name.toLowerCase() + "." + surname.toLowerCase() + "@example.com";

            Visitor visitor = new Visitor(name, surname, email);
            visitors.add(visitor);
            
        }

       
	}

}
