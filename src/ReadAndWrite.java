/**
 * A class to read files and write using object stream
 * The purpose of this class is just to load the contents of file as an arraylist
 * and also to write it back as a list
 * */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWrite {

    // a function to read a file from disk and load it as List
    public List<Task> readTaskAsAList() {
        List<Task> loadedFromFile = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream("t.ser")) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            loadedFromFile = (List<Task>)  ois.readObject();
            ois.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }
        return loadedFromFile;
    }

    //writes the list back to the memory
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
            System.out.println("File Not found ");;
        } catch (IOException ex) {
            System.out.println("Input/output error");
        }
    }

}
