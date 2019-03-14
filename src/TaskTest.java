import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class TaskTest {

    private LocalDate dueDate;
    private String taskName;
    private String project;
    private Task taskUnderTest;

    @Before
    public void setUp() {
        dueDate = LocalDate.of(2017, 1, 1);
        taskName = "taskName";
        project = "project";
        taskUnderTest = new Task(dueDate, taskName, project);
    }

    @Test
    public void testIsDone() {
        // Setup
        final String expectedResult = "Not Done";

        // Run the test
        final String result = taskUnderTest.isDone();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
