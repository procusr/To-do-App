/**
 * This is class is mainly responsible for editing, removing and changing the status of
 * the tasks
 * */

import java.util.List;

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

    public List<Task> removeTask(int id,List<Task> tasks){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.remove(id);
        return tasks;
    }

    public List<Task> editTask(int id,List<Task> tasks,String editedTask){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setTaskName(editedTask);
        return tasks;
    }

}



