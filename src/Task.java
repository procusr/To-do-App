import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {
    private String task;
    private boolean done;
    private LocalDate dueDate;
    private String project;
    private List<Task> todoItems;
    private LocalDate date;

    public Task(LocalDate dueDate, String taskName, String project) {
        this.task = taskName;
        this.dueDate = dueDate;
        this.todoItems=new ArrayList<>();
        this.date=LocalDate.now();
        this.done=false;
    }
    public Task() {
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public void setTaskName(String task) {
        this.task = task;
    }

    public boolean getStatus() {
        return this.done;
    }

    public void setStatus(boolean done) {
        this.done = done;
    }
    public String getProject(){
       return this.project ;
    }

    public void setProject(String project){this.project=project;}

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    // represents the done and undone tasks in human readable form
    public  String isDone() {
        if(this.done == true) {
            return "Done";
        }
         return "Not Done";
    }
}

