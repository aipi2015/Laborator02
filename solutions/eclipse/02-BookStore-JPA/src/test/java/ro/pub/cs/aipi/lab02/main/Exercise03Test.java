package ro.pub.cs.aipi.lab02.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import ro.pub.cs.aipi.lab02.businesslogic.PersistentEntityManager;
import ro.pub.cs.aipi.lab02.entities.User;

public class Exercise03Test {

	final private static int TABLE_NUMBER_OF_RECORDS = 2000;

	@BeforeClass
	public static void executeExercise03() {
		BookStore bookstore = new BookStore();
		Long result = bookstore.exercise03();
		if (Long.valueOf(result) != (TABLE_NUMBER_OF_RECORDS + 1)) {
			fail("The record was not inserted!");
		}
	}

	@Test
	public void checkNumberOfRecords() {
		System.out.println("Test03 -INSERT- checkNumberOfRecords");
		assertEquals("Table user should have " + (TABLE_NUMBER_OF_RECORDS + 1) + " records!",
				(TABLE_NUMBER_OF_RECORDS + 1),
				(new PersistentEntityManager<User>(User.class)).getPersistentEntitySize());
		System.out.println("Test passed!");
	}

}
