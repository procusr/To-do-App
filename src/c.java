import java.util.List;
import java.util.Scanner;

public class c {
    private Scanner input;
    private ReadAndWrite rw ;
    private List<Task> myTasks;
    private EditTodoList editor ;
    private DisplayTodoList display;

    public c(){
        input = new Scanner(System.in);
        rw = new ReadAndWrite();
        myTasks = rw.readTaskAsAList();
        editor = new EditTodoList();
        display = new DisplayTodoList();
    }
    public static void main(String[] args) {
        c cc = new c();
        Scanner in = new Scanner(System.in);
        List<Task> task = cc.rw.readTaskAsAList();
        cc.display.displayList(task);
        System.out.println("Enter the existing prj: ");


    }




}
