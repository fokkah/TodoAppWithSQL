package fokka.se.Interface;


import fokka.se.Todo.Person;
import fokka.se.Todo.TodoItem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface TodoItems {
    TodoItem create(TodoItem todoItem);
    ArrayList<TodoItem> findAll();
    TodoItem findById(int id) throws SQLException;
    ArrayList<TodoItem> findByDoneStatus(boolean todoItem) throws SQLException;
    ArrayList<TodoItem> findByAssignee(int id);
    ArrayList<TodoItem> findByAssignee(Person person);
    ArrayList<TodoItem> findByUnassignedTodoItems();
    TodoItem update(TodoItem todoItem);
    Boolean deleteById(int id);




}
