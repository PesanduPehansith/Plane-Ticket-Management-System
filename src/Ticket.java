import java.io.FileWriter;
import java.io.IOException;

public class Ticket {
    // Initializing variables
    private char row;
    private int seat;
    private double price;
    private Person person;

    // Creating a class constructor
    public Ticket(char row, int seat, double price, Person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // Adding all the getters and setters
    // Getter
    public char getRow() {
        return row;
    }
    // Setter
    public void setRow(char row) {
        this.row = row;
    }

    // Getter
    public int getSeat() {
        return seat;
    }
    // Setter
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // getter
    public double getPrice() {
        return price;
    }
    // Setter
    public void setPrice(double price) {
        this.price = price;
    }

    // Getter
    public Person getPerson() {
        return person;
    }

    // Setter
    public void setPerson(Person person) {
        this.person = person;
    }

    // Writing the information of the ticket and the person to a file using a method and handling exceptions
    public void saveTicket() {
        try (FileWriter writer = new FileWriter(row + String.valueOf(seat)+ ".txt")) {
            writer.write("Row: " + row + "\n" + "Seat: " + seat + "\n" + "Price: £" + price );
            writer.write("\nPerson name: " + person.getName() + "\nPerson surname: " + person.getSurname() + "\nPerson email: " + person.getEmail());
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Printing information of the ticket using a method
    public void ticket_details(){
        System.out.println("Row: " + row);
        System.out.println("seat: " + seat);
        System.out.println("Price: £" + price);
        person.person_details(); // Calling the person_details methods of class Person
    }
}