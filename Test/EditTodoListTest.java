import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EditTodoListTest {
    private Task task,task1;
    private EditTodoList editTodoListUnderTest;

    @Before
    public void setUp() {
        editTodoListUnderTest = new EditTodoList();
        task = new Task(LocalDate.now(),"task","prj12");
        task1 = new Task(LocalDate.now(),"task","prj12");
    }

    @Test
    public void testRemoveTask() {
        //setup
        final int id = 0;
        final List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task1);
        final List<Task> expectedResult = Arrays.asList(task1);

       // test
        final List<Task> result = editTodoListUnderTest.removeTask(id, tasks);

        // Verify
        assertEquals(expectedResult.get(0).getTask(), result.get(0).getTask());
    }


    // this test to work there must be a file stored with the specified tasks.
    @Test
    public void testEditTask() {
        // Setup
        final int id = 0;
        final List<Task> tasks = Arrays.asList(task,task1);
        final String expectedResult = "editedTask";

        // the test
        final List<Task> result = editTodoListUnderTest.editTask(id, tasks,"editedTask");

        // Verify
        assertEquals(expectedResult, result.get(0).getTask());
    }
}
