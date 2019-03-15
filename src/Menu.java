/**
 * This is the main class which is responsible for calling other classes and run
 * methods to display main menu,display and write/read from memory and manipulate tasks.
 * */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Menu {
    private Scanner input;
    private ReadAndWrite rw;
    private List<Task> myTasks;
    private EditTodoList editor;
    private DisplayTodoList display;

    //constructor
    public Menu() {
        input = new Scanner(System.in);
        rw = new ReadAndWrite();
        myTasks = rw.readTaskAsAList();
        editor = new EditTodoList();
        display = new DisplayTodoList();
    }

    // A menu for our text-based user interface
    public void menuDisplay() {
        String string_input;
        int int_input;
        do {
            while (true) {                   //user is asked to enter the correct number and format if not provided
                display.displayMainMenu();
                try {
                    string_input = input.nextLine();
                    int_input = Integer.parseInt(string_input);
                    if (int_input >= 0 && int_input <= 9) {
                        break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Enter a valid option again ");
                }
            }
            switch (int_input) {
                case 0:
                    addTodo();
                    break;
                case 1:
                    displayYourTodoList();
                    break;
                case 2:
                    removeATask();
                    break;
                case 3:
                    editTask();
                    break;
                case 4:
                    display.displayByDate(rw.readTaskAsAList());
                    break;
                case 5:
                    display.sortByProject(rw.readTaskAsAList());
                    break;
                case 6:
                    markAsDone();
                    break;
                case 7:
                    System.out.println("Here are the task(s) you're going to do ...");
                    display.ShowNotDone(rw.readTaskAsAList());
                    break;
                case 8:
                    showTasksByProject();
                default:
                    System.out.println("-- Good Bye --");
            }
        }
        while (!(int_input == 9));
        rw.writeToDoList(myTasks);  //write everything on exit to a file
    }

    //add a task with appropriate date
    public void addTodo() {
        Task task = new Task();
        List<Task> myTasks = rw.readTaskAsAList();
        System.out.print("Enter the Task: ");
        while (input.hasNextLine()) {             //keep looping until user enters valid input
            String taskName = input.nextLine();
            if (!taskName.isEmpty()) {
                task.setTaskName(taskName);
                break;
            }
            System.out.print("Enter the task: ");
        }
        System.out.print("Enter date in the format(dd-mm-yyyy): ");
        LocalDate date = parseDate(input);
        while (LocalDate.now().compareTo(date) > 0) {      //Validate if the given date is not before today
            System.out.print("Your given due date is already passed or You entered " +
                    "Invalid character\nEnter your input again:");
            date = parseDate(input);
        }
        task.setDueDate(date);
        System.out.print("Project Name: ");
        while (input.hasNextLine()) {      //makes sure the user enters valid input
            String projectName = input.nextLine();
            if (!projectName.isEmpty()) {
                task.setProject(projectName);
                myTasks.add(task);
                rw.writeToDoList(myTasks);
                break;
            }
            System.out.print("Enter the project name please: ");
        }
    }

    // removes the task based on the id provided in the displayed menu
    public void removeATask() {
        display.readFileAndDisplayList();
        System.out.print("Enter the Id of the Task to remove ...");
        int x;
        while (true) {
            try {
                x = Integer.parseInt(input.nextLine());
                if (x < rw.readTaskAsAList().size()) {
                    List<Task> edited = editor.removeTask(x, rw.readTaskAsAList());
                    rw.writeToDoList(edited);
                    break;
                }
                System.out.print("Enter Again Please: ");
            } catch (IndexOutOfBoundsException | InputMismatchException | NumberFormatException ex) {
                System.out.println("Enter numbers only: ");
            }
        }
    }

    // Shows all Saved tasks
    public void displayYourTodoList() {
        display.readFileAndDisplayList();
    }


    //Takes Scanner object and parses the input given by the user
    public LocalDate parseDate(Scanner in) {
        LocalDate parsedDate = null;
        while (true) {
            try {
                parsedDate = LocalDate.parse(in.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                break;
            } catch (Exception e) {
                System.out.print("You did not provide the proper format/input \n" +
                        "Enter again (dd-MM-yyyy): ");
            }
        }
        return parsedDate;
    }

    //edits the Specified task and Automatically saves it
    public void editTask() {
        display.readFileAndDisplayList();
        System.out.println("Enter Task of the Id Your would like to edit: ");
        int x;
        String y;
        while (true) {
            try {
                y = input.nextLine();
                x = Integer.parseInt(y);
                if (x < rw.readTaskAsAList().size()) {
                    System.out.println("Edit Your task ...");
                    String editedText = input.nextLine();
                    List<Task> modified = editor.editTask(x, rw.readTaskAsAList(), editedText);
                    rw.writeToDoList(modified);
                    break;
                }
                System.out.println("Enter a valid option please: ");
            } catch (IndexOutOfBoundsException | InputMismatchException | NumberFormatException ex) {
                System.out.println("That task doesn't exist enter again: ");
            }
        }

    }

    //Change the state of the task (Done/undone)
    public void markAsDone() {
        display.readFileAndDisplayList();
        System.out.println("Select the index of a task to mark it as done");
        int x;
        String y;
        while (true) {
            try {
                y = input.nextLine();
                x = Integer.parseInt(y);
                if (x < rw.readTaskAsAList().size()) {
                    editor.markAsDone(x, rw.readTaskAsAList());
                    break;
                }
                System.out.println("Enter a valid option please");
            } catch (IndexOutOfBoundsException | InputMismatchException | NumberFormatException ex) {
                System.out.println("That task doesn't exist enter again: ");
            }
        }
    }

    //return a list of tasks by project's name
    public void showTasksByProject(){
        System.out.println("Enter Project name: ");
        display.showProjectByName(rw.readTaskAsAList(),input.nextLine());
    }
}








