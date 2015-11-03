package ro.pub.cs.aipi.lab02.main;

import org.junit.BeforeClass;
import org.junit.Test;

public class Exercise08Test {

	@BeforeClass
	public static void executeExercise08() {
		BookStore bookstore = new BookStore();
		bookstore.exercise08();
	}

	@Test
	public void checkFileContent() {
		System.out.println("Test08 -SELECT- checkFileContent");
		AllTests.fileComparison("08books.txt");
	}

}
