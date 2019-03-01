/**
 * Displays the main menu, A list of tasks in the memory and shows sorted by date and project
 *
 * */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayTodoList {
    ReadAndWrite rw;
    private EditTodoList editor ;

    public DisplayTodoList(){
        rw = new ReadAndWrite();
    }

    public void displayMainMenu(){
        System.out.println("------------------------------------------------");
        displayDoneAndUndone(rw.readTaskAsAList());
        System.out.println("------------------------------------------------");
        System.out.println("1.Add to do list \t\t2.View Your Tasks");
        System.out.println("3.Remove a Task \t\t4.Edit Task");
        System.out.println("5.Sort by Date\t\t    6.Group by project");
        System.out.println("7.Mark Task as done\t\t8.Quit");
        System.out.println("------------------------------------------------");
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


    public void readFileAndDisplayList() {
        List<Task> loadedFromFile = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("t.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedFromFile = (List<Task>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File noy found");
        } catch (IOException ex) {
            System.out.println("Input/output error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Not found");
        }
        displayList(loadedFromFile);
    }

    public void displayList(List<Task> task) {
        System.out.format("%80s%n", "Your Current Tasks are:-");
        String format = "%-4s%-15s%-90s%-35s%-10s%n";
        System.out.printf(format, "ID", "Date Created", "Task", "Project", "Status");
        System.out.printf(format, "━━", "━━━━━━━━━━━━", "━━━━━━━━━", "━━━━━━━━━", "━━━━━━━━");
        for (int i = 0; i < task.size(); i++) {
            System.out.printf(format, i,
                    task.get(i).getDueDate(), task.get(i).getTask(),
                    task.get(i).getProject(), task.get(i).isDone());
        }
    }

    public void sortByProject(List<Task> tasks){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->task.getProject())).collect(Collectors.toList());
        displayList(sorted);
    }

    public void sortBytype(List<Task> tasks,String content){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->content)).collect(Collectors.toList());
        displayList(sorted);
    }
}

