public class Person {
    // Initializing variables
    private String name;
    private String surname;
    private String email;

    // Creating a class constructor
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Adding all the getters and setters
    // Getter
    public String getName(){
        return name;
    }
    // Setter
    public void setName(String name){
        this.name=name;
    }

    // Getter
    public String getSurname(){
        return surname;
    }
    // Setter
    public void setSurname(String surname){
        this.surname=surname;
    }

    // Getter
    public String getEmail(){
        return email;
    }
    // Setter
    public void setEmail(String email){
        this.email=email;
    }

    // Printing information of the person using a method
    public void person_details(){
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Email: " + email);
        System.out.println();
    }
}