package fokka.se.Todo;

public class TodoItemTask {

    //Fields-----------------------------------------------------------------------------

    private int id;
    private boolean assigned;
    private TodoItem todoItem;
    private Person assignee;
    String summary;

    //Fields end-----------------------------------------------------------------------------
    //Constructors-----------------------------------------------------------------------------

    public TodoItemTask(boolean assigned, TodoItem todoItem, Person assignee) {
        this.assigned = assigned;
        this.todoItem = todoItem;
        this.assignee = assignee;
    }

    //Constructors end-----------------------------------------------------------------------------
    //Setters and Getters-----------------------------------------------------------------------------


    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
        if (assigned == true) {
        }
            else {
                throw new RuntimeException("Task is already assigned or is not assigned");
        }
    }



    public void setTodoItem(TodoItem todoItem) {
        this.todoItem = todoItem;
        if (todoItem == null || todoItem.toString().trim().isEmpty()) {
            throw new RuntimeException("TodoItem cant be null or empty");
        }
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
        if (assigned == true || assignee == null || assignee.toString().trim().isEmpty()) {
            throw new RuntimeException("Assignee cant be null or empty or assigned");

        }
    }

    //Getters-----------------------------------------------------------------------------


    public int getId() {
        return id;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public TodoItem getTodoItem() {
        return todoItem;
    }

    public Person getAssignee() {
        return assignee;
    }

    public String getSummary() {
        return summary;
    }
}
