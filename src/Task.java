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


    public Task(LocalDate dueDate, String taskName, String project, String taskDescription) {
        this.task = taskName;
        this.dueDate = dueDate;
        this.project = taskDescription;
        this.todoItems=new ArrayList<>();
        this.date=LocalDate.now();
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

    public void setTaskDescription(String project){
        this.project=project;
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

    public  String isDone() {
        if(this.done == true) {
            return "Done";
        }
         return "Not Done";
    }

}

