import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DisplayTodoList {


    public DisplayTodoList(){

    }
    public void readFileAndDisplayList() {
        List<Task> loadedFromFile = new ArrayList<Task>();
        try (FileInputStream fis = new FileInputStream("t.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedFromFile = (List<Task>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File noy found");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
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
}

