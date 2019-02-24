import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    private Scanner input;
    private ReadAndWrite rw = new ReadAndWrite();
    private List<Task> myTasks = rw.readTaskAsAList();
    private EditTodoList editor = new EditTodoList();
    private DisplayTodoList display = new DisplayTodoList();

    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.input = new Scanner(System.in);
        int n;
        do {
            System.out.println("------------------------------------------------");
            myApp.editor.displayDoneAndUndone(myApp.rw.readTaskAsAList());
            System.out.println("------------------------------------------------");
            System.out.println("1.Add to do list \t\t2.View Your Tasks");
            System.out.println("3.Remove a Task \t\t4.Edit Task");
            System.out.println("5.Sort by Date\t\t    6.Group by project");
            System.out.println("7.Mark Task as done\t\t8.Save and Quit");
            System.out.println("------------------------------------------------");
            n = myApp.input.nextInt();
            myApp.input.nextLine();

            switch (n) {
                case 1:
                    myApp.addTodo();
                    break;
                case 2:
                    myApp.displayYourTodoList();
                    break;
                case 3:
                    myApp.removeATask();
                    break;
                case 4:
                   myApp.editTask();
                   break;
                case 5:
                    myApp.editor.displayByDate(myApp.rw.readTaskAsAList());
                    break;
                case 6:
                    myApp.display.sortByProject(myApp.rw.readTaskAsAList());
                case 7:
                    myApp.markAsDone();
                default:
                    System.out.println("Goodbye !!");
            }
        }
        while (n != 8);
        myApp.rw.writeToDoList(myApp.myTasks);
    }
    public void addTodo() {
        Task task = new Task();
        List<Task> myTasks = rw.readTaskAsAList();
        System.out.println("Enter the Task: ");
        task.setTaskName(input.nextLine());
        System.out.println("Enter date (dd-mm-yyyy)");
        LocalDate date = parseDate(input.nextLine());
        while(LocalDate.now().compareTo(date)>0){      //Validate if the given date is not before today
            System.out.println("Your given due date is already passed or You entered " +
                    "Invalid character Enter your input again");
             date = parseDate(input.nextLine());
        }
        task.setDueDate(date);
        //System.out.println("Describe the Task: ");
        //task.setTaskDescription(input.nextLine());
        System.out.println("Project Name: ");
        task.setProject(input.nextLine());
        myTasks.add(task);
        rw.writeToDoList(myTasks);
 }

    public void removeATask(){
        System.out.println("Enter the Id of the Task to remove");
        display.readFileAndDisplayList();
        List<Task> edited = editor.removeTask(input.nextInt(),rw.readTaskAsAList());
        rw.writeToDoList(edited);
    }

    public void displayYourTodoList(){
        display.readFileAndDisplayList();
    }

    public static LocalDate parseDate(String string) {
        LocalDate s = null;
        try {
            s = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH));
            LocalDate r = s;
        } catch (Exception e) {
            System.out.println("You did not provide the proper format/input");
        }
        return s;

    }

    public void editTask(){
        display.readFileAndDisplayList();
        System.out.println("Enter Task of the Id Your would like to edit  ");
        int id = input.nextInt();
        input.nextLine();
        System.out.println("Edit Your task ...");
        String editedText = input.nextLine();
        List<Task> modified = editor.editTask(id,rw.readTaskAsAList(),editedText);
        rw.writeToDoList(modified);
    }

    public void markAsDone(){
        display.readFileAndDisplayList();
        System.out.println("Select the index of a task to mark it as done");
        editor.markAsDone(input.nextInt(),rw.readTaskAsAList());
    }

}





