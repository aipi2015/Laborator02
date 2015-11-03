package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.List;

import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.result.BookInformation;
import ro.pub.cs.aipi.lab02.result.BookInformationDetailed;

public class BookManager extends PersistentEntityManager<Book> {

	public BookManager() {
		super(Book.class);
	}

	public List<BookInformation> getBooksList() {
		List<BookInformation> result = new ArrayList<>();

		// TODO: exercise 2

		return result;
	}

	public List<BookInformationDetailed> getBooksListWithStockpile(int stockpile) {
		List<BookInformationDetailed> result = new ArrayList<>();

		// TODO: exercise 8

		return result;
	}

}
