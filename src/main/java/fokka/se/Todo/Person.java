package fokka.se.Todo;

public class Person  {

    private int id;
    private String firstName;
    private String lastName;


    public Person(int id, String firstName, String lastName) {
        this(firstName, lastName);
        this.id = id;

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName) {
        this.firstName = firstName;
    }



    //--------------------



    public void setId(int id) {

        this.id = id;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new RuntimeException("Lastname cant be Null or empty");

        }
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new RuntimeException("Firstname cant be Null or empty");
        }
    }


    //----------------------


    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
