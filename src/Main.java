/**
 *
 * This is the main class which is responsible for calling other classes and run
 * methods to display main menu,display and write/read from memory and manipulate tasks
 *
 * */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private Scanner input;
    private ReadAndWrite rw ;
    private List<Task> myTasks;
    private EditTodoList editor ;
    private DisplayTodoList display;

    public Main(){
         input = new Scanner(System.in);
         rw = new ReadAndWrite();
         myTasks = rw.readTaskAsAList();
         editor = new EditTodoList();
         display = new DisplayTodoList();
    }

    public static void main(String[] args) {
        Main myApp = new Main();
        myApp.input = new Scanner(System.in);


      // A menu for our text-based user interface
       String n ;
       int x;
            do {
                while(true) {
                    myApp.display.displayMainMenu();
                    try {
                         n = myApp.input.nextLine();
                         x =Integer.parseInt(n);
                    if(!(x<=0||x>=9)){
                            break;
                        }
                    } catch (NumberFormatException ex) {
                        System.out.println("Enter a valid option again ");
                    }
                }

                switch (x) {
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
                        myApp.display.displayByDate(myApp.rw.readTaskAsAList());
                        break;
                    case 6:
                        myApp.display.sortByProject(myApp.rw.readTaskAsAList());
                    case 7:
                        myApp.markAsDone();
                    default:
                        System.out.println("Goodbye !!");
                }

            }
            while (x != 8);
            myApp.rw.writeToDoList(myApp.myTasks);
        }

    public void addTodo() {
        Task task = new Task();
        List<Task> myTasks = rw.readTaskAsAList();
        System.out.print("Enter the Task: ");
        while(input.hasNextLine()){
            String taskName = input.nextLine();
            if(!taskName.isEmpty()) {
                task.setTaskName(taskName);
                break;
            }
            System.out.print("Enter the task: ");
        }
        System.out.print("Enter date in the format(dd-mm-yyyy): ");
        LocalDate date = parseDate(input);
        while(LocalDate.now().compareTo(date)>0 && input.nextLine().isEmpty()){      //Validate if the given date is not before today
            System.out.println("Your given due date is already passed or You entered " +
                    "Invalid character\nEnter your input again");
             date = parseDate(input);
        }
        task.setDueDate(date);
        System.out.println("Project Name: ");
        while(input.hasNextLine()){
            String projectName = input.nextLine();
            if(!projectName.isEmpty()) {
                task.setProject(projectName);
                myTasks.add(task);
                rw.writeToDoList(myTasks);
                break;
            }
            System.out.print("Enter the project name please: ");
        }
    }

    // removes the task based on the id provided in the displayed menu
    public void removeATask(){
        display.readFileAndDisplayList();
        System.out.print("Enter the Id of the Task to remove ...");
        int x;
        while(true){
            try {
                x =Integer.parseInt(input.nextLine());
                if(x<rw.readTaskAsAList().size()){
                    List<Task> edited = editor.removeTask(x,rw.readTaskAsAList());
                    rw.writeToDoList(edited);
                    break;
                }
                System.out.print("Enter Again Please");
            }
            catch(IndexOutOfBoundsException|InputMismatchException|NumberFormatException ex){
                System.out.println("Enter numbers only: ");
            }
        }
    }
       // Shows all Saved tasks
    public void displayYourTodoList(){
        display.readFileAndDisplayList();
    }


    //Takes Scanner object and parses the input given by the user

    public static LocalDate parseDate(Scanner in) {
       boolean condition=true;
        LocalDate s = null;
        while(condition) {
            try {
                s = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH));
                condition=false;
            } catch (Exception e) {
                System.out.println("You did not provide the proper format/input");
            }
        }
        return s;
    }

    //edits the Specified task and Automatically saves it
    public void editTask(){
        display.readFileAndDisplayList();
        System.out.println("Enter Task of the Id Your would like to edit  ");
        int x;
        String y;
        while(true) {
            try {
                y = input.nextLine();
                x =Integer.parseInt(y);
                if(x<rw.readTaskAsAList().size()){
                    System.out.println("Edit Your task ...");
                    String editedText = input.nextLine();
                    List<Task> modified = editor.editTask(x,rw.readTaskAsAList(),editedText);
                    rw.writeToDoList(modified);
                    break;
                }
                System.out.println("Enter a valid option please");
            }
            catch(IndexOutOfBoundsException|InputMismatchException|NumberFormatException ex){
                System.out.println("That task doesn't exist enter again: ");
            }
        }

    }

    //Change the state of the task (Done/undone)
    public void markAsDone(){
        display.readFileAndDisplayList();
        System.out.println("Select the index of a task to mark it as done");
        int x;
        String y;
        while(true){
            try {
                 y = input.nextLine();
                 x =Integer.parseInt(y);
                 if(x<rw.readTaskAsAList().size()){
                    editor.markAsDone(x,rw.readTaskAsAList());
                    break;
                }
                System.out.println("Enter a valid option please");
            }
        catch(IndexOutOfBoundsException|InputMismatchException|NumberFormatException ex){
            System.out.println("That task doesn't exist enter again: ");
            }
        }
    }
}





