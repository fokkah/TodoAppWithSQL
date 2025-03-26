package fokka.se.DAO;

import fokka.se.DB.SQLconnection;
import fokka.se.Interface.People;
import fokka.se.Todo.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAOImpl implements People {


    @Override
    public Person create(Person person) throws SQLException {
        Connection connection = SQLconnection.getConnection();
        String sql = "INSERT INTO person (person_id, first_name, last_name)";

        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setInt(1, person.getId());
        ps.setString(2, person.getFirstName());
        ps.setString(3, person.getLastName());

        int result = ps.executeUpdate();

        return person ;
    }

    @Override
    public ArrayList<Person> findAll() throws SQLException {

        Connection connection = SQLconnection.getConnection();
        Statement statement = connection.createStatement();

        String sql = "SELECT person_id, first_name, last_name";
        ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("person_id");
                String firstName = rs.getString("first_name");
                String last_name = rs.getString("last_name");

                System.out.println("ID: " + id + " Name: " + firstName + " " + last_name );
            }
        return null;

    }

    @Override
    public Person findById(int id) throws SQLException {


        return new Person(id, "", "");
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
