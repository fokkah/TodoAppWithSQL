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

        try (

            Connection connection = SQLconnection.getConnection();
        ){
        Person person = new Person(1, "Tamas", "Szalai");
        PersonDAOImpl newPersonDAO = new PersonDAOImpl(connection);

        //newPersonDAO.create(person);
        newPersonDAO.findAll().forEach(System.out::println);


        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        System.out.println(person);

//
//       if (connection != null){
//           System.out.println("Database connection established");
//       }




    }

}