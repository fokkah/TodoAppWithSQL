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
        ArrayList<Person> findByNameArrayList = new ArrayList<>();

        String sql = "SELECT person_id, first_name, last_name FROM person where first_name =? OR last_name = ?";
        //String sqlLN = "SELECT * FROM person where last_name = ?";
        //String sqlName = sqlFN + sqlLN;

        try (

                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, person);
            ps.setString(2, person);
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                while (rs.next()) {
                    int id = rs.getInt("person_id");
                    String firsName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    //if (person.equals(sqlFN) || person.equals(sqlLN)) {}

                    findByNameArrayList.add(new Person(id, firsName, lastName));

                }
                return findByNameArrayList;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        throw new IndexOutOfBoundsException("No such Name exists");
    }


    @Override
    public Person update(Person person) throws SQLException {
        String sql = "UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            try {
                ps.setString(1, person.getFirstName());
                ps.setString(2, person.getLastName());
                ps.setInt(3, person.getId());

                ps.executeUpdate();
                //int rowsAffected = ps.executeUpdate();
                //if (rowsAffected > 0) {
                //System.out.println("Done" + person);
                //} else {
                //  System.out.println("No rows affected" + person.getId());
                //}

                return person;
            } catch (SQLException e) {
                System.out.println("Error updating the person" + e.getMessage());
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM person WHERE person_id = ? ";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            try {
                ps.setInt(1, id);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Error deleting the person" + e.getMessage());
                e.printStackTrace();
            }

        }
        return false;
    }
}

