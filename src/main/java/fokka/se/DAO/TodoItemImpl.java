package fokka.se.DAO;

import fokka.se.Interface.TodoItems;
import fokka.se.Todo.Person;
import fokka.se.Todo.TodoItem;
import jdk.swing.interop.SwingInterOpUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        ) {
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

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (

                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {
                    int todo_id = rs.getInt(1);
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");
                    Person assignee = personDAO.findById(rs.getInt("assignee_id"));

                    return new TodoItem(todo_id, title, description, deadline, done, assignee);
                }

            } catch (SQLException e) {
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

        try (

                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setBoolean(1, todoItem);
            try (

                    ResultSet rs = ps.executeQuery()
            ) {

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
            } catch (SQLException e) {
                System.out.println("Couldnt connect to DB" + e.getMessage());
                e.printStackTrace();
            }
            throw new SQLException("Couldnt find that Status");

        }
    }

    @Override
    public ArrayList<TodoItem> findByAssignee(int id) throws SQLException {
        ArrayList<TodoItem> assigneeArraylist = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assignee_id =?";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {

                    int todo_id = rs.getInt("todo_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");
                    Person assignee = personDAO.findById(rs.getInt("assignee_id"));

                    assigneeArraylist.add(new TodoItem(todo_id, title, description, deadline, done, assignee));
                    return assigneeArraylist;
                }
            } catch (SQLException e) {
                System.out.println("Couldnt connect to DB" + e.getMessage());
                e.printStackTrace();

            }
            throw new RuntimeException("Couldnt find that asignee");
        }
    }

    @Override
    public ArrayList<TodoItem> findByAssignee(Person person) {
        ArrayList<TodoItem> assigneeArraylistPerson = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assignee_id =?";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, person.getId());
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {

                    int todo_id = rs.getInt("todo_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");
                    Person assignee = personDAO.findById(rs.getInt("assignee_id"));

                    assigneeArraylistPerson.add(new TodoItem(todo_id, title, description, deadline, done, assignee));
                }
            } catch (SQLException e) {
                System.out.println("Couldnt connect to DB" + e.getMessage());
                e.printStackTrace();

            }
        } catch (SQLException e) {
            throw new RuntimeException("Couldnt find that asignee");

        }
        return assigneeArraylistPerson;
    }

    @Override
    public ArrayList<TodoItem> findByUnassignedTodoItems() throws SQLException {
        ArrayList<TodoItem> unassignedTodoArrayList = new ArrayList<>();
        String sql = "SELECT * FROM todo_item WHERE assigned_id =?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {
            ps.setInt(1, 0);
            try (
                    ResultSet rs = ps.executeQuery()
            ) {
                if (rs.next()) {
                    int todo_id = rs.getInt("todo_id");
                    String title = rs.getString("title");
                    String description = rs.getString("description");
                    LocalDate deadline = rs.getDate("deadline").toLocalDate();
                    boolean done = rs.getBoolean("done");

                    unassignedTodoArrayList.add(new TodoItem(todo_id, title, description, deadline, done, null));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return unassignedTodoArrayList;
        }

    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        String sql = "UPDATE todo_item SET " +
                "todo_id =?, " +
                "title =?, " +
                "description =?, " +
                "deadline =?, " +
                "done = ?, " +
                "assignee_id =? " +
                " WHERE todo_id =?";
        PersonDAOImpl personDAO = new PersonDAOImpl(connection);

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            try {

                ps.setInt(1, todoItem.getId());
                ps.setString(2, todoItem.getTitle());
                ps.setString(3, todoItem.getDescription());
                ps.setDate(4, java.sql.Date.valueOf(todoItem.getDeadLine()));
                ps.setBoolean(5, todoItem.isDone());
                ps.setInt(6, todoItem.getCreator().getId());
                ps.setInt(7, todoItem.getId());
                ps.executeUpdate();

                return todoItem;

            } catch (SQLException e) {
                System.out.println("Error updating the object: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Couldnt connect to DB" + e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("Couldnt find that guy");
    }

    @Override
    public Boolean deleteById(int id) {
        String sql = "DELETE FROM todo_item WHERE todo_id =?";

        try (
                PreparedStatement ps = connection.prepareStatement(sql)
                ){
            try {
                ps.setInt(1, id);
                ps.executeUpdate();

                return true;

            }catch (SQLException e) {
                System.out.println("Error delete the object: " + e.getMessage());
                e.printStackTrace();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
            return false;
    }
}
