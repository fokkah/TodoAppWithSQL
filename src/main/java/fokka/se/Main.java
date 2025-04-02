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



//            Person person1 = new Person("Tamas");

//
//            personDAOImpl.create(person1);
//            System.out.println(personDAOImpl.findAll());
//            System.out.println(personDAOImpl.findById(10));
//            System.out.println(personDAOImpl.findByName("Tamas"));
//            System.out.println(personDAOImpl.update(person1));
//            System.out.println(personDAOImpl.deleteById(12));







//            TodoItem todoItem1 = new TodoItem("titleTestForReview", "descTestForReview", person1, LocalDate.now().plusDays(3));
//            TodoItem todoItem = new TodoItem("title", "desc", person1, LocalDate.now().plusDays(12));
//            System.out.println(todoItemImpl.create(new TodoItem("titleTestForReview", "descTestForReview", person1, LocalDate.now().plusDays(3))));
//            System.out.println(todoItemImpl.create(new TodoItem("titleForReview", "descForReview", personDAOImpl.findById(11), LocalDate.now())));
//            System.out.println(todoItemImpl.findAll());
//            System.out.println(todoItemImpl.findById(11));
//            System.out.println(todoItemImpl.findByDoneStatus(true));
//            System.out.println(todoItemImpl.findByAssignee(10));
//            System.out.println(todoItemImpl.findByAssignee(personDAOImpl.findById(11)));
//            System.out.println(todoItemImpl.findByUnassignedTodoItems());





        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }


    }
}