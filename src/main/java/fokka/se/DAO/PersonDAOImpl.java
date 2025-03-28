package fokka.se.DAO;

import fokka.se.Interface.People;
import fokka.se.Todo.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAOImpl implements People {

    private final Connection connection;

    public PersonDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Person create(Person person) throws SQLException {
        String sql = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        try (

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            //connection.setAutoCommit(false);
            //Auto commit kollar ifall alla las till rätt innan den lägger till i DB. Måste köra connection.commit efter allt är executat.

            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getLastName());

            int result = ps.executeUpdate();
            //connection.commit();
            if (result > 0) {
                System.out.println("Done");
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    person.setId(id);
                }
            }

        } catch (Exception e) {
            System.out.println("There was a problem creating the object" + e.getMessage());
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public ArrayList<Person> findAll() throws SQLException {

        ArrayList<Person> personArrayList = new ArrayList<>();

        String sql = "SELECT * from person";
        try (
                Statement createstatement = connection.createStatement();
                ResultSet rs = createstatement.executeQuery(sql)
        ) {

            while (rs.next()) {
                int id = rs.getInt("person_id");
                String firstName = rs.getString("first_name");
                String last_name = rs.getString("last_name");


                personArrayList.add(new Person(id, firstName, last_name));

//                personArrayList.add(new Person(
//                        rs.getInt("person_id"),
//                        rs.getString("first_name"),
//                        rs.getString("last_name") ));

            }

        } catch (Exception e) {
            System.out.println("Couldnt retrieve data" + e.getMessage());
            e.printStackTrace();
        }
        return personArrayList;


    }

    @Override
    public Person findById(int id) throws SQLException {


        String sql = "SELECT * from PERSON where person_id = ?";
        try
                (PreparedStatement ps = connection.prepareStatement(sql)
                ) {
            ps.setInt(1, id);
            try (

                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {
                    int personId = rs.getInt("person_id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");

                    return new Person(id, firstName, lastName);
                }
            }
        } catch (SQLException e) {
            System.out.println("Couldnt find ID" + e.getMessage());
            e.printStackTrace();
        }
        throw new IndexOutOfBoundsException("No such ID exists");
    }

    @Override
    public ArrayList<Person> findByName(String person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
