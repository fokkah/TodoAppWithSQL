package fokka.se.Interface;

import fokka.se.Todo.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface People {

    Person create(Person person) throws SQLException;
    ArrayList<Person> findAll() throws SQLException;
    Person findById(int id) throws SQLException;
    ArrayList<Person> findByName(String person);
    Person update(Person person) throws SQLException;
    boolean deleteById(int id);


}
