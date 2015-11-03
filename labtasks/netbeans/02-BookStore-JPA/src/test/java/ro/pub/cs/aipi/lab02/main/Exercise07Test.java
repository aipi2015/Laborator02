package ro.pub.cs.aipi.lab02.main;

import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise07Test {

    @BeforeClass
    public static void executeExercise11() {
        BookStore bookstore = new BookStore();
        bookstore.exercise07();
    }

    @Test
    public void checkFileContent() {
        System.out.println("Test07 -TRANSACTION- checkFileContent");
        AllTests.fileComparison("07supply_orders.txt");
    }
}
