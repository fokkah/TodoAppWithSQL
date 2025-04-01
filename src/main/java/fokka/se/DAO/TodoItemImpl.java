package fokka.se.DAO;

import fokka.se.Interface.TodoItems;
import fokka.se.Todo.Person;
import fokka.se.Todo.TodoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TodoItemImpl implements TodoItems {
    private final Connection connection;

    public TodoItemImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TodoItem create(TodoItem todoItem) {
        String sql = "INSERT INTO todo_item (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?,?)";
        try (
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            ps.setString(1, todoItem.getTitle());
            ps.setString(2, todoItem.getDescription());
            ps.setDate(3, java.sql.Date.valueOf(todoItem.getDeadLine()));
            ps.setBoolean(4, todoItem.isDone());
            ps.setInt(5, todoItem.getCreator().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Success!");

        return todoItem;
    }

    @Override
    public ArrayList<TodoItem> findAll() {
        ArrayList<TodoItem> itemArrayList = new ArrayList<>();
        String sql = "SELECT * from todo_item";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try (
                PreparedStatement findAllPreperedStatement = connection.prepareStatement(sql);
                ResultSet rs = findAllPreperedStatement.executeQuery()
        ) {
            while (rs.next()) {
                int id = rs.getInt("todo_id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                LocalDate deadline = rs.getDate("deadline").toLocalDate();
                boolean done = rs.getBoolean("done");
                Person assignee = personDAO.findById(rs.getInt("assignee_id"));


                    //itemArrayList.add(findById(rs.getInt(1)));


                itemArrayList.add(new TodoItem(id, title, description, deadline, done, assignee));


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return itemArrayList;
    }

        @Override
    public TodoItem findById(int id) throws SQLException {
        String sql = "SELECT * FROM todo_item WHERE todo_id =?";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try(
            PreparedStatement ps = connection.prepareStatement(sql);
            ) {
                ps.setInt(1, id);
            try (

            ResultSet rs = ps.executeQuery();
                    ){
                if (rs.next()) {
                    int todo_id = rs.getInt(1);
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");
                    Person assignee = personDAO.findById(rs.getInt("assignee_id"));

                    return new TodoItem(todo_id, title, description, deadline, done, assignee);
                }

            }catch (SQLException e) {
                System.out.println("Couldnt connect to DB" + e.getMessage());
                e.printStackTrace();
            }
        }
        throw new SQLException("Couldnt find id: " + id);
    }

    @Override
    public ArrayList<TodoItem> findByDoneStatus(boolean todoItem) throws SQLException {
        ArrayList<TodoItem> doneStatusArrayList = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE done =?";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try(

        PreparedStatement ps = connection.prepareStatement(sql);
        ){
            ps.setBoolean(1, todoItem);
            try (

            ResultSet rs = ps.executeQuery();
            ){

                if (rs.next()) {
                    int todo_id = rs.getInt("todo_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");
                    Person assignee = personDAO.findById(rs.getInt("assignee_id"));

                    doneStatusArrayList.add(new TodoItem(todo_id, title, description, deadline, done, assignee));
                    return doneStatusArrayList;
                }
            }catch (SQLException e) {
                System.out.println("Couldnt connect to DB" + e.getMessage());
                e.printStackTrace();
            }
        throw new SQLException("Couldnt find that Status");

        }
    }

    @Override
    public ArrayList<TodoItem> findByAssignee(int id) {
        return null;
    }

    @Override
    public ArrayList<TodoItem> findByAssignee(Person person) {
        return null;
    }

    @Override
    public ArrayList<TodoItem> findByUnassignedTodoItems() {
        return null;
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        return null;
    }

    @Override
    public Boolean deleteById(int id) {
        return null;
    }
}
