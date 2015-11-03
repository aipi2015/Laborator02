package ro.pub.cs.aipi.lab02.businesslogic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.entities.BookPresentation;
import ro.pub.cs.aipi.lab02.entities.BookPresentation_;
import ro.pub.cs.aipi.lab02.entities.Category;
import ro.pub.cs.aipi.lab02.entities.Collection;
import ro.pub.cs.aipi.lab02.entities.Country;
import ro.pub.cs.aipi.lab02.entities.Format;
import ro.pub.cs.aipi.lab02.entities.Language;
import ro.pub.cs.aipi.lab02.entities.PublishingHouse;
import ro.pub.cs.aipi.lab02.entities.Writer;
import ro.pub.cs.aipi.lab02.general.Utilities;
import ro.pub.cs.aipi.lab02.result.BookInformation;
import ro.pub.cs.aipi.lab02.result.BookInformationDetailed;

public class BookManager extends PersistentEntityManager<Book> {

	public BookManager() {
		super(Book.class);
	}

	public List<BookInformation> getBooksList() {
		EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
		List<Book> booksList = getCollection(entityManager);
		List<BookInformation> result = new ArrayList<>();
		for (Book book : booksList) {
			BookInformation bookInformation = new BookInformation();
			bookInformation.setId(book.getId());
			bookInformation.setTitle(book.getTitle());
			StringBuilder writers = new StringBuilder();
			for (Writer writer : book.getWriters()) {
				writers.append(writer.toString() + ", ");
			}
			if (writers.length() > 0) {
				writers.setLength(writers.length() - 2);
			} else {
				writers.append("* * *");
			}
			bookInformation.setWriters(writers.toString());
			Collection collection = book.getCollection();
			bookInformation.setCollection(collection.getName());
			PublishingHouse publishingHouse = collection.getPublishingHouse();
			bookInformation.setPublishingHouse(publishingHouse.getName());
			bookInformation.setEdition(book.getEdition());
			bookInformation.setPrintingYear(book.getPrintingYear());
			Country country = publishingHouse.getCountry();
			bookInformation.setCountry(country.getName());
			result.add(bookInformation);
		}
		entityManager.close();
		return result;
	}

	public List<BookInformationDetailed> getBooksListWithStockpile(int stockpile) {
		List<BookInformationDetailed> result = new ArrayList<>();

		EntityManager entityManager = Utilities.getEntityManagerFactory().createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQueryBook = criteriaBuilder.createQuery(Book.class);
		Root<BookPresentation> rootBookPresentation = criteriaQueryBook.from(BookPresentation.class);
		criteriaQueryBook.select(rootBookPresentation.get(BookPresentation_.book));
		criteriaQueryBook.groupBy(rootBookPresentation.get(BookPresentation_.book));
		criteriaQueryBook.having(criteriaBuilder
				.gt(criteriaBuilder.sum(rootBookPresentation.get(BookPresentation_.stockpile)), stockpile));
		TypedQuery<Book> typedQueryBook = entityManager.createQuery(criteriaQueryBook);
		List<Book> books = typedQueryBook.getResultList();

		for (Book book : books) {
			BookInformationDetailed bookInformationDetailed = new BookInformationDetailed();
			bookInformationDetailed.setId(book.getId());
			bookInformationDetailed.setTitle(book.getTitle());
			bookInformationDetailed.setSubtitle(book.getSubtitle());
			Set<Writer> writersSet = book.getWriters();
			List<String> writersList = new ArrayList<>();
			for (Writer writer : writersSet) {
				writersList.add(writer.toString());
			}
			Collections.sort(writersList);
			StringBuilder writers = new StringBuilder();
			for (String writer : writersList) {
				writers.append(writer + ", ");
			}
			if (writers.length() > 0) {
				writers.setLength(writers.length() - 2);
			}
			bookInformationDetailed.setWriters(writers.toString());
			Set<Category> categoriesSet = book.getCategories();
			List<String> categoriesList = new ArrayList<>();
			for (Category category : categoriesSet) {
				categoriesList.add(category.getName());
			}
			Collections.sort(categoriesList);
			StringBuilder categories = new StringBuilder();
			for (String category : categoriesList) {
				categories.append(category + ", ");
			}
			if (categories.length() > 0) {
				categories.setLength(categories.length() - 2);
			}
			bookInformationDetailed.setCategories(categories.toString());
			CriteriaQuery<BookPresentation> criteriaQueryBookPresentationForBook = criteriaBuilder
					.createQuery(BookPresentation.class);
			Root<BookPresentation> rootBookPresentationForBook = criteriaQueryBookPresentationForBook
					.from(BookPresentation.class);
			criteriaQueryBookPresentationForBook.select(rootBookPresentationForBook);
			criteriaQueryBookPresentationForBook
					.where(criteriaBuilder.equal(rootBookPresentationForBook.get(BookPresentation_.book), book));
			TypedQuery<BookPresentation> typedQueryBookPresentationForBook = entityManager
					.createQuery(criteriaQueryBookPresentationForBook);
			List<BookPresentation> bookPresentations = typedQueryBookPresentationForBook.getResultList();
			List<String> formatList = new ArrayList<>();
			List<String> languageList = new ArrayList<>();
			for (BookPresentation bookPresentation : bookPresentations) {
				Format format = bookPresentation.getFormat();
				Language language = bookPresentation.getLanguage();
				if (!formatList.contains(format.getValue())) {
					formatList.add(format.getValue());
				}
				if (!languageList.contains(language.getName())) {
					languageList.add(language.getName());
				}
			}
			Collections.sort(formatList);
			Collections.sort(languageList);
			StringBuilder formats = new StringBuilder();
			for (String format : formatList) {
				formats.append(format + ", ");
			}
			if (formats.length() > 0) {
				formats.setLength(formats.length() - 2);
			}
			bookInformationDetailed.setFormats(formats.toString());
			StringBuilder languages = new StringBuilder();
			for (String language : languageList) {
				languages.append(language + ", ");
			}
			if (languages.length() > 0) {
				languages.setLength(languages.length() - 2);
			}
			bookInformationDetailed.setLanguages(languages.toString());
			result.add(bookInformationDetailed);
		}
		entityManager.close();
		return result;
	}

}
