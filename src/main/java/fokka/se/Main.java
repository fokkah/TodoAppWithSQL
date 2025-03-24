package fokka.se;

import fokka.se.DAO.PersonDAOImpl;
import fokka.se.DB.SQLconnection;
import fokka.se.Interface.People;
import fokka.se.Todo.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

//        Person person = new Person(1, "Tamas", "Szalai");
//        System.out.println(person);

        Connection connection = SQLconnection.getConnection();

       if (connection != null){
           System.out.println("Database connection established");
       }


    }
}