package ro.pub.cs.aipi.lab02.main;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class Exercise01Test {

	final private static HashMap<String, Integer> persistentEntityNumberOfRecords = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 20152015L;

		{
			put("Book", 2382);
			put("BookPresentation", 7028);
			put("Category", 156);
			put("Collection", 160);
			put("Country", 240);
			put("Format", 30);
			put("InvoiceHeader", 2000);
			put("InvoiceLine", 11076);
			put("Language", 185);
			put("PublishingHouse", 547);
			put("SupplyOrderHeader", 2000);
			put("SupplyOrderLine", 11124);
			put("User", 2000);
			put("Writer", 2586);
		}
	};

	private String persistentEntity;

	public Exercise01Test(String persistentEntity) {
		this.persistentEntity = persistentEntity;
	}

	@Parameters
	public static Collection<Object[]> data() {
		ArrayList<Object[]> data = new ArrayList<>();
		Set<String> persistentEntities = persistentEntityNumberOfRecords.keySet();
		Iterator<String> persistentEntitiesIterator = persistentEntities.iterator();
		while (persistentEntitiesIterator.hasNext()) {
			String persistentEntity = persistentEntitiesIterator.next();
			data.add(new Object[] { persistentEntity });
		}
		return data;
	}

	@Test
	public void checkNumberOfRecords() {
		System.out.println("Test01 -SELECT- checkNumberOfRecords - parameter: " + persistentEntity);
		BookStore bookstore = new BookStore();
		int numberOfRecords = persistentEntityNumberOfRecords.get(persistentEntity);
		assertEquals("Table " + persistentEntity + " should have " + numberOfRecords + " records!", numberOfRecords,
				bookstore.exercise01(persistentEntity));
		System.out.println("Test passed!");
	}

}
