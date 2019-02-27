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
               // myApp.input.nextLine();

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
            while (x != 8);
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
                    "Invalid character\nEnter your input again");
             date = parseDate(input.nextLine());
        }
        task.setDueDate(date);
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
       boolean r=true;
        LocalDate s = null;
       while(r) {
            try {
                s = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH));
                r=false;
            } catch (Exception e) {
                System.out.println("You did not provide the proper format/input");
                s = LocalDate.parse(string, DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH));
            }
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
        int x;
        String y;
        while(true){
            try {
                 y = input.nextLine();
                System.out.println("Enter a valid option please");
                 x =Integer.parseInt(y);
                if(x<rw.readTaskAsAList().size()){
                    break;
                }
            }
        catch(IndexOutOfBoundsException|InputMismatchException|NumberFormatException ex){
            System.out.println("That task doesn't exist enter again: ");
            }
        }
        editor.markAsDone(x,rw.readTaskAsAList());

    }
}







