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

    public DisplayTodoList(){
        rw = new ReadAndWrite();
    }

    //A method to Display main menu
    public void displayMainMenu(){
        System.out.println("------------------------------------------------");
        displayDoneAndUndone(rw.readTaskAsAList());
        System.out.println("------------------------------------------------");
        System.out.println("(0).Add to do list         \t(1).View Your Tasks");
        System.out.println("(2).Remove a Task          \t(3).Edit Task");
        System.out.println("(4).Sort by Date           \t(5).Sort Tasks by project");
        System.out.println("(6).Mark Task as done      \t(7).Show only Undone tasks");
        System.out.println("(8).Show project with tasks (9).Quit");
        System.out.println("------------------------------------------------");
    }

    //Counts the number of tasks done and incomplete
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
        System.out.println("You have " + done_tasks + "  task(s) Completed  and  " + undone_taks + "  Task(s) to do");
    }

    //Reads from a file and and display its contents as a list
    public void readFileAndDisplayList() {
        List<Task> loadedFromFile = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("t.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedFromFile = (List<Task>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Input/output error");
        } catch (ClassNotFoundException ex) {
            System.out.println("Not found");
        }
        displayTasks(loadedFromFile);
    }

    //
    public void displayTasks(List<Task> task) {
        System.out.format("%80s%n", "Your Current Task(s) are:-");
        String format = "%-4s%-15s%-90s%-35s%-10s%n";
        System.out.printf(format, "ID", "Due Date", "Task", "Project", "Status");
        System.out.printf(format, "━━", "━━━━━━━━━━━━", "━━━━━━━━━", "━━━━━━━━━", "━━━━━━━━");
        for (int i = 0; i < task.size(); i++) {
            System.out.printf(format, i,
                    task.get(i).getDueDate(), task.get(i).getTask(),
                    task.get(i).getProject(), task.get(i).isDone());
        }
    }

    //Sorts the projects alphabetically
    public void sortByProject(List<Task> tasks){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->task.getProject(),String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
        displayTasks(sorted);
    }
    //Sort the days in ascending order
    public void displayByDate(List<Task> tasks){
        List<Task> sorted = tasks.stream().sorted(Comparator.comparing(task->task.getDueDate())).collect(Collectors.toList());
        displayTasks(sorted);
    }
        //Display only incomplete tasks
    public void ShowNotDone(List<Task> tasks){
        List<Task> notDone = tasks.stream().filter(task -> !task.getStatus()).collect(Collectors.toList());
        displayTasks(notDone);
    }

    //Display tasks with with given project
    public void showProjectByName(List<Task> tasks,String project) {
        tasks = tasks.stream().filter(task -> task.getProject().equals(project)).collect(Collectors.toList());
        displayTasks(tasks);
    }
}

