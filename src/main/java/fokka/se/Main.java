package fokka.se;

import fokka.se.DAO.PersonDAOImpl;
import fokka.se.DAO.TodoItemImpl;
import fokka.se.DB.SQLconnection;
import fokka.se.Interface.People;
import fokka.se.Todo.Person;
import fokka.se.Todo.TodoItem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {


        try (
            Connection connection = SQLconnection.getConnection();
        ){
            PersonDAOImpl personDAOImpl = new PersonDAOImpl(connection);
            TodoItemImpl todoItemImpl = new TodoItemImpl(connection);

            // lägg till personer till DB
            personDAOImpl.create(new Person("Tamas", "Szalai"));

            Person person1 = new Person("Tamas");

            // lägg till items till DB

            TodoItem todoItem = new TodoItem("title", "desc", person1, LocalDate.now().plusDays(12));

            // testa metoder för item och person.



        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }


    }
}