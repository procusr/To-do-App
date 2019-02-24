import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ReadAndWrite {
    private List<Task> loadedFromFile;

    public static void main(String[] args) {
        Main m = new Main();

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

        return loadedFromFile;
    }

    public void writeToDoList(List<Task> tasks) {

        File f = new File("t.ser");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
            oos.flush();
            System.out.println("\t  To Do List Saved ✔✔✔");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}