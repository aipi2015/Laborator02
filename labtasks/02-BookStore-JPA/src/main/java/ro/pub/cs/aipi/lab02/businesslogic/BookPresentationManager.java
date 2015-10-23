package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab02.entities.BookPresentation;
import ro.pub.cs.aipi.lab02.result.BookPresentationInformation;

public class BookPresentationManager extends PersistentEntityManager<BookPresentation> {

	public BookPresentationManager() {
		super(BookPresentation.class);
		persistentEntity = "BookPresentation";
	}

	public List<BookPresentationInformation> updateBookPresentationPriceForBooksWithMultipleFormats(int numberOfFormats,
			double amount) {

		List<BookPresentationInformation> result = new ArrayList<>();

		// TODO: exercise 4

		return result;
	}
}
