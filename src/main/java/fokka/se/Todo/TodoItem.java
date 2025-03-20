package fokka.se.Todo;

import java.time.LocalDate;
import java.util.Objects;

public class TodoItem {

    //----------Fields-----------------------------------------------------------------------------

    private int todo_id;
    private String title;
    private String description;
    private LocalDate deadLine;
    private boolean done;
    private Person assignee_id;

    //----------Fields End-----------------------------------------------------------------------------
    //----------Constructors-----------------------------------------------------------------------------


    public TodoItem(int todo_id, String title, String description, LocalDate deadLine, boolean done, Person assignee_id) {
        this(title, description, assignee_id, deadLine);
        this.todo_id = todo_id;
    }
    public TodoItem(String title, String description, Person assignee, LocalDate deadLine) {

        this.title = title;
        this.description = description;
        this.assignee_id = assignee;
        this.deadLine = deadLine;
    }

    //----------Constructors  End -----------------------------------------------------------------------------
    //----------Setters and Getters-----------------------------------------------------------------------------

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new RuntimeException("Title cant be Null or empty");

        }
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeadLine(LocalDate deadLine) {
        if (LocalDate.now().isAfter(deadLine)) {
            throw new RuntimeException("Deadline has past and work is Overdue");
        }
        this.deadLine = deadLine;
    }

    public void setDone(boolean done) {
        //Todo: Fix the done status
        this.done = done;
    }

    public void setCreator(Person assignee_id) {
        //if (assignee_id == null || assignee_id.toString().isEmpty()) {
        //    throw new RuntimeException("Creator cant be null or empty");
        //Samma skit som ovan
        Objects.requireNonNull(assignee_id);
        this.assignee_id = assignee_id;
    }

    //
    //--------------Getters-----------------------------------------------------------------------------
    //

    public int getId() {
        return todo_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public Person getCreator() {
        return assignee_id;
    }

    public boolean isOverDue() {
        return LocalDate.now().isAfter(deadLine);
    }


    //-----------------Setters and Getters End---------------------------------------------------------------------


    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + todo_id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return todo_id == todoItem.todo_id && done == todoItem.done && Objects.equals(title, todoItem.title) && Objects.equals(description, todoItem.description) && Objects.equals(deadLine, todoItem.deadLine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(todo_id, title, description, deadLine, done);
    }
}
