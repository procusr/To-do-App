
//this class is working fine with reading and writing the proper way!!
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class FileLoader {

    public FileLoader(){

    }




    public static void writeToDoList(List<Task> tasks) {

        File f = new File("t.ser");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            oos.flush();
            System.out.format("\t ✔ To Do List Saved ✔");

        } catch (FileNotFoundException ex) {
            System.out.println("File not found ");
        } catch (IOException ex) {
            System.out.println("Something went wrong");
        }
    }

    public List<Task> readTaskAsAList() {
        List<Task> loadedFromFile = new ArrayList<Task>();
        try (FileInputStream fis = new FileInputStream("t.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);


            loadedFromFile = (List<Task>) ois.readObject();

            ois.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

        for (Task t : loadedFromFile) {
            System.out.println("-------Your Current Tasks are-------- ");
            System.out.println(t.getProject() + t.getTask() + t.getDueDate());
        }
        return loadedFromFile;
    }




}
