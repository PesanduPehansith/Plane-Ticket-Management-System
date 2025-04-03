import java.util.*;

public class w2052813_PlaneManagement {

    // Initializing number of rows
    private static final int NO_OF_ROWS = 4;

    // Initializing number of seats in each row using an array
    private static final int[] SEATS_IN_ROW = {14, 12, 12, 14};

    // Array to store seats information (available or sold)
    private static char[][] seats = new char[NO_OF_ROWS][];

    // Arrays to store sold ticket information
    private static Ticket[] tickets = new Ticket[total_seats()];

    public static void main(String[] args) {
        // Calling the seats_order method
        seats_order();
        // Printing welcome message.
        System.out.println("\n  Welcome to the Plane Management application.");
        // Creating a scanner object to get user inputs
        Scanner input = new Scanner(System.in);

        int option;
        do {
            // Calling the menu_option method
            menu_options();
            while (true) {
                // Getting user option and validating it
                System.out.print("Please select an option: ");
                while (!input.hasNextInt()) {
                    System.out.println("Invalid Option! Please enter a valid option.\n");
                    System.out.print("Please select an option: ");
                    input.next();
                }
                option = input.nextInt();

                // Calling methods according the user input options using a switch case
                switch (option) {
                    case 1:
                        buy_seat();
                        break;
                    case 2:
                        cancel_seat();
                        break;
                    case 3:
                        find_first_available();
                        break;
                    case 4:
                        show_seating_plan();
                        break;
                    case 5:
                        print_tickets_info();
                        break;
                    case 6:
                        search_ticket();
                        break;
                    case 0:
                        System.out.println("You Exited From Plane Management Application.");
                        break;
                    default:
                        System.out.println("Invalid Option! Please enter a valid option.\n");
                        continue;
                }
                break;
            }
        }
        // Looping until user input 0 to exit the application
        while (option != 0);
    }

    // Displaying menu options using a method
    private static void menu_options() {
        System.out.println();
        System.out.println("************************************************");
        System.out.println("*                MENU OPTIONS                  *");
        System.out.println("************************************************");
        System.out.println("    1) Buy a seat");
        System.out.println("    2) Cancel a seat");
        System.out.println("    3) Find first available seat");
        System.out.println("    4) Show seating plan");
        System.out.println("    5) Print tickets information and total sales");
        System.out.println("    6) Search ticket");
        System.out.println("    0) Quit");
        System.out.println("************************************************");
    }

    // Initializing all seats to '0' (Available) using a method
    private static void seats_order() {
        for (int i = 0; i < NO_OF_ROWS; i++) {
            seats[i] = new char[SEATS_IN_ROW[i]];
            for (int x = 0; x < SEATS_IN_ROW[i]; x++) {
                seats[i][x] = 'O';
            }
        }
    }

    // Buying a seats using a method
    private static void buy_seat() {
        // Creating a scanner object to get user inputs
        Scanner input = new Scanner(System.in);
        while (true) {
            // Getting user input row letter and validating it
            System.out.print("Enter the row letter (A, B, C, D): ");
            char row_letter = input.next().toUpperCase().charAt(0);
            // Calling the getRowIndex method
            int row_num = getRowIndex(row_letter);
            if (row_num >= 0 && row_num < NO_OF_ROWS) {
                while (true) {
                    try {
                        // Getting user input seat number
                        System.out.print("Enter the seat number [A- (1-14), B- (1-12), C -(1-12), D- (1-14)]: ");
                        int seat_number = input.nextInt();
                        // Checking whether the seat is available
                        if (seat_number > 0 && seat_number <= SEATS_IN_ROW[row_num] && seats[row_num][seat_number - 1] == 'O') {
                            // Marking the seat as sold
                            seats[row_num][seat_number - 1] = 'X';
                            while (true) {
                                // Getting user input name and validating it
                                System.out.print("Enter the name: ");
                                String name = input.next();
                                if (name.matches("[a-zA-z]+")) {
                                    while (true) {
                                        // Getting user input surname and validating it
                                        System.out.print("Enter the surname: ");
                                        String surname = input.next();
                                        if (surname.matches("[a-zA-z]+")) {
                                            while (true) {
                                                // Getting user input email and validating it
                                                System.out.print("Enter the email: ");
                                                String email = input.next();
                                                if (email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
                                                    // Creating new person object
                                                    Person person = new Person(name, surname, email);
                                                    // Calling the ticket prices method
                                                    double price = ticket_prices(row_letter, seat_number);
                                                    // Creating a new ticket object
                                                    Ticket ticket = new Ticket(row_letter, seat_number, price, person);
                                                    // Calling the ticket index method
                                                    int index = ticket_index(row_letter, seat_number);
                                                    // Storing the ticket information in ticket array
                                                    tickets[index] = ticket;
                                                    System.out.println("Seat sold successfully.");
                                                    // Calling saveTicket method
                                                    ticket.saveTicket();
                                                } else {
                                                    System.out.println("Invalid Input! Please enter the email in 'abc@gmail.com' format.\n");
                                                    continue;
                                                }
                                                break;
                                            }
                                        } else {
                                            System.out.println("Invalid Input! Please enter only letters (a-z)(A-Z)\n");
                                            continue;
                                        }
                                        break;
                                    }
                                }else {
                                    System.out.println("Invalid Input! Please enter only letters (a-z)(A-Z)\n");
                                    continue;
                                }
                                break;
                            }

                        }else {
                            System.out.println("Invalid seat number or the seat is already sold.\n");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException e){
                        System.out.println("Invalid Input! Please enter a number.\n");
                        input.next();
                    }
                }
            } else {
                System.out.println("Invalid Input! Enter a valid row letter.\n");
                continue;
            }
            break;
        }
    }

    // Method to generate row index
    private static int getRowIndex(char row){
        return row - 'A';
    }

    // Cancelling a seat using a method
    private static void cancel_seat() {
        // Creating a scanner object to get user inputs
        Scanner input = new Scanner(System.in);
        while (true) {
            // Getting user input row letter and validating it
            System.out.print("Enter the row letter (A, B, C, D): ");
            char row_letter = input.next().toUpperCase().charAt(0);
            // Calling the getRowIndex method
            int row_num = getRowIndex(row_letter);
            if (row_num >= 0 && row_num < NO_OF_ROWS) {
                while (true) {
                    try {
                        // Getting user input seat number
                        System.out.print("Enter the seat number [A- (1-14), B- (1-12), C -(1-12), D- (1-14)]: ");
                        int seat_number = input.nextInt();
                        // Checking whether the seat is sold
                        if (seat_number > 0 && seat_number <= SEATS_IN_ROW[row_num] && seats[row_num][seat_number - 1] == 'X') {
                            // Marking the seat as available
                            seats[row_num][seat_number - 1] = 'O';
                            // Calling the ticket index method
                            int index = ticket_index(row_letter, seat_number);
                            // Removing the ticket information stored in the ticket array
                            tickets[index] = null;
                            System.out.println("Seat cancelled successfully.");
                        } else {
                            System.out.println("Invalid seat number or the seat is not sold.\n");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid Input! Please enter a number.\n");
                        input.next();
                    }
                }
            }else {
                System.out.println("Invalid Input! Enter a valid row letter.\n");
                continue;
            }
            break;
        }
    }

    // Searching for the first available seat using a method
    private static void find_first_available() {
        for (int i = 0; i < NO_OF_ROWS; i++) {
            char row = (char) ('A' + i);
            for (int j = 0; j < SEATS_IN_ROW[i]; j++) {
                // Checking whether a seat is available and displaying the row letter and seat number
                if (seats[i][j] == 'O') {
                    System.out.println("First available seat: Row " + row + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("SORRY, All seats are sold at the moment");
    }

    // Method to display the seating plan
    private static void show_seating_plan() {
        System.out.println("** Available seats are displayed with 'O' and sold seats are displayed with 'X' **");
        System.out.println("************************************************");
        System.out.println("*               SEATING PLAN                   *");
        System.out.println("************************************************");

        for (int i = 0; i < NO_OF_ROWS; i++) {
            char rows = (char) ('A' + i);
            // Displaying the row number in the seating plan
            System.out.print("Row " + rows + ": ");
            for (int j = 0; j < SEATS_IN_ROW[i]; j++) {
                // Displaying available seats with 'O' and sold seats with 'X' while keeping a space between seat number 7 and set number 8
                if (seats[i][j] == 'O') {
                    if (j + 1 == 7) {
                        System.out.print("O  ");
                    } else {
                        System.out.print("O ");
                    }
                } else {
                    if (j + 1 == 7) {
                        System.out.print("X  ");
                    } else {
                        System.out.print("X ");
                    }
                }
            }
            // Printing the next line
            System.out.println();
        }
    }


    // Initializing the total number of seats using a method
    private static int total_seats() {
        int totalNumberOfSeats = 0;
        for (int i = 0; i < NO_OF_ROWS; i++) {
            totalNumberOfSeats += SEATS_IN_ROW[i];
        }
        return totalNumberOfSeats;
    }

    // Initializing the ticket prices using a method
    private static double ticket_prices(char row, int seat_number){
        // Deciding ticket prices based on seat number
        if (seat_number <= 5) {
            return 200;
        }
        else if (seat_number <= 9) {
            return 150;
        }
        else {
            return 180;
        }
    }

    // Method to generate ticket index
    private static int ticket_index(char row, int seat_number) {
        // Finding index of row
        int rowIndex = row - 'A';
        // Finding index of seat
        int index = seat_number - 1;
        for (int i = 0; i < rowIndex; i++) {
            index += SEATS_IN_ROW[i];
        }
        return index;

    }


    // Printing the ticket information using a method
    private static void print_tickets_info(){
        double total = 0;
        for(Ticket ticket: tickets){
            // Checking whether the ticket exist
            if (ticket != null){
                // Calling ticket details method in Ticket class
                ticket.ticket_details();
                total += ticket.getPrice();
            }
        }
        // Displaying the total price of tickets sold
        System.out.println("Total amount: £" + total);
    }

    // Searching a ticket using a method
    private static void search_ticket(){
        // Creating a scanner object to get user inputs

        Scanner input = new Scanner(System.in);
        while (true) {
            // Getting user input row letter and validating it
            System.out.print("Enter the row letter (A, B, C, D): ");
            char row_letter = input.next().toUpperCase().charAt(0);
            // Calling the getRowIndex method
            int row_num = getRowIndex(row_letter);

            if (row_num >= 0 && row_num < NO_OF_ROWS) {
                while (true) {
                    try {
                        // Getting the user input seat number and validating it
                        System.out.print("Enter the seat number [A- (1-14), B- (1-12), C -(1-12), D- (1-14)]: ");
                        int seat_number = input.nextInt();

                        if (seat_number > 0 && seat_number <= SEATS_IN_ROW[row_num]) {
                            // calling the ticket index method
                            int index = ticket_index(row_letter, seat_number);
                            // Checking whether the ticket exist
                            if (tickets[index] != null) {
                                // Displaying the ticket and person information
                                tickets[index].ticket_details();
                            } else {
                                System.out.println("This seat is available.");
                            }
                        } else {
                            System.out.println("Invalid Input! Please enter a valid seat number.\n");
                            continue;
                        }
                        break;
                    } catch (InputMismatchException e){
                        System.out.println("Invalid Input! Please enter a number.\n");
                        input.next();
                    }
                }
            } else {
                System.out.println("Invalid Input! Enter a valid row letter.\n");
                continue;
            }
            break;
        }
    }
}