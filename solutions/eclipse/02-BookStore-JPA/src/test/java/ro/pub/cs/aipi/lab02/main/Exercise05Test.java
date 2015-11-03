package ro.pub.cs.aipi.lab02.main;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.BeforeClass;
import org.junit.Test;

import ro.pub.cs.aipi.lab02.entities.Writer;
import ro.pub.cs.aipi.lab02.entities.Writer_;
import ro.pub.cs.aipi.lab02.general.Constants;
import ro.pub.cs.aipi.lab02.general.Utilities;

public class Exercise05Test {

	final private static int NUMBER_OF_RECORDS = 649;

	@BeforeClass
	public static void executeExercise05() {
		BookStore bookstore = new BookStore();
		int result = bookstore.exercise05();
		System.out.println("Test05 -DELETE- checkNumberOfRecords");
		if (result != NUMBER_OF_RECORDS) {
			fail("The records were not deleted! " + result);
		} else {
			System.out.println("Test passed!");
		}
	}

	@Test
	public void checkFileContent() {
		System.out.println("Test05 -DELETE- checkFileContent");
		Path inputFile = Paths.get(Constants.INPUT_DIRECTORY + "/05writers.txt");
		assertTrue("Reference file does not exist or cannot be accessed!",
				inputFile != null && Files.isRegularFile(inputFile) && Files.isReadable(inputFile));
		Charset charset = Charset.forName("UTF-8");
		EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
		try (BufferedReader inputFileBufferedReader = Files.newBufferedReader(inputFile, charset)) {
			String inputFileCurrentLine = null;
			do {
				long id = -1;
				inputFileCurrentLine = inputFileBufferedReader.readLine();
				if (inputFileCurrentLine != null) {
					id = Long.parseLong(inputFileCurrentLine);
					CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
					CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
					Root<Writer> root = criteriaQuery.from(Writer.class);
					criteriaQuery.select(criteriaBuilder.count(root.get(Writer_.id)));
					criteriaQuery.where(criteriaBuilder.equal(root.get(Writer_.id), id));
					TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
					long numberOfBooks = typedQuery.getSingleResult();
					assertEquals("writer " + id + " should have been deleted", 0, numberOfBooks);
				}
			} while (inputFileCurrentLine != null);
			System.out.println("Test passed!");
		} catch (IOException ioException) {
			fail("An exception has occurred!" + ioException.getMessage());
			if (Constants.DEBUG) {
				ioException.printStackTrace();
			}
		}
	}
}
