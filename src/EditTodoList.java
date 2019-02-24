import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EditTodoList {
    private Task task;
    private ReadAndWrite readAndWriteToFile;
    private DisplayTodoList display;

    public EditTodoList(){
        this.task = new Task();
        this.readAndWriteToFile = new ReadAndWrite();
        this.display=new DisplayTodoList();
    }
    public void markAsDone(int id,List<Task> tasks){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setStatus(true);
        readAndWriteToFile.writeToDoList(tasks);

    }
    public void markAsUnDone(int id,List<Task> tasks){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setStatus(false);

    }

    public List<Task> removeTask(int id,List<Task> tasks){
        List<Task> edited = new ArrayList<>();                                    //to store edited list
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.remove(id);
        edited = tasks;
        return edited;
    }

    public List<Task> editTask(int id,List<Task> tasks,String editedTask){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setTaskName(editedTask);
        return tasks;

    }
    public void displayDoneAndUndone(List<Task> tasks){
        int done_tasks=0,undone_taks=0;
        for(Task task:tasks){
            if(task.getStatus()==true){
                done_tasks++;
            }
            else{
                undone_taks++;
            }
        }
        System.out.println("You have " + done_tasks + "  tasks Completed  and  " + undone_taks + "  Tasks to do");
    }

    public void displayByDate(List<Task> tasks){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->task.getDueDate())).collect(Collectors.toList());
        display.displayList(sorted);
        }
}



