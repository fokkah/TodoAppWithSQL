package fokka.se;

import fokka.se.DAO.PersonDAOImpl;
import fokka.se.DB.SQLconnection;
import fokka.se.Interface.People;
import fokka.se.Todo.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try (
            Connection connection = SQLconnection.getConnection();

        ){
        Person person = new Person("Tamas", "Szalai");
        PersonDAOImpl newPersonDAO = new PersonDAOImpl(connection);

        //newPersonDAO.create(person);
        //newPersonDAO.findAll().forEach(System.out::println);
        //System.out.println(newPersonDAO.findById(2));
            System.out.println(newPersonDAO.findByName("Tamas"));


        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

//        System.out.println(person);

//
//       if (connection != null){
//           System.out.println("Database connection established");
//       }




    }

}