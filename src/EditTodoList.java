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
        while(!(id<tasks.size())) {
            tasks.get(id).setStatus(true);
            readAndWriteToFile.writeToDoList(tasks);
        }

    }
    public void markAsUnDone(int id,List<Task> tasks){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setStatus(false);

    }

    public List<Task> removeTask(int id,List<Task> tasks){//to store edited list
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.remove(id);
        return tasks;
    }

    public List<Task> editTask(int id,List<Task> tasks,String editedTask){
        tasks = readAndWriteToFile.readTaskAsAList();
        tasks.get(id).setTaskName(editedTask);
        return tasks;

    }


    public void displayByDate(List<Task> tasks){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->task.getDueDate())).collect(Collectors.toList());
        display.displayList(sorted);
        }
}



