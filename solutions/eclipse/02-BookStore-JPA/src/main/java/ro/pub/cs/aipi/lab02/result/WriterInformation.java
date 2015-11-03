package ro.pub.cs.aipi.lab02.result;

import ro.pub.cs.aipi.lab02.entities.PersistentEntity;

public class WriterInformation extends PersistentEntity {

	protected String writer;
	protected String books;
	protected Long numberOfBooksTotal;
	protected Long numberOfBooksAlone;
	protected Long numberOfBooksCollaboration;

	public WriterInformation() {
	}

	public WriterInformation(String writer, String books, Long numberOfBooksTotal, Long numberOfBooksAlone,
			Long numberOfBooksCollaboration) {
		this.writer = writer;
		this.books = books;
		this.numberOfBooksTotal = numberOfBooksTotal;
		this.numberOfBooksAlone = numberOfBooksAlone;
		this.numberOfBooksCollaboration = numberOfBooksCollaboration;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getBooks() {
		return books;
	}

	public void setBooks(String books) {
		this.books = books;
	}

	public Long getNumberOfBooksTotal() {
		return numberOfBooksTotal;
	}

	public void setNumberOfBooksTotal(Long numberOfBooksTotal) {
		this.numberOfBooksTotal = numberOfBooksTotal;
	}

	public Long getNumberOfBooksAlone() {
		return numberOfBooksAlone;
	}

	public void setNumberOfBooksAlone(Long numberOfBooksAlone) {
		this.numberOfBooksAlone = numberOfBooksAlone;
	}

	public Long getNumberOfBooksCollaboration() {
		return numberOfBooksCollaboration;
	}

	public void setNumberOfBooksCollaboration(Long numberOfBooksCollaboration) {
		this.numberOfBooksCollaboration = numberOfBooksCollaboration;
	}

}
