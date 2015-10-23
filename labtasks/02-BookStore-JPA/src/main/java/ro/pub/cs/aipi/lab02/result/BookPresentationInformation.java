package ro.pub.cs.aipi.lab02.result;

import ro.pub.cs.aipi.lab02.entities.Book;
import ro.pub.cs.aipi.lab02.entities.PersistentEntity;

public class BookPresentationInformation extends PersistentEntity {

	protected Long id;
	protected Book book;
	protected Double price;

	public BookPresentationInformation() {
	}
	
	public BookPresentationInformation(Long id, Book book, Double price) {
		this.id = id;
		this.book = book;
		this.price = price;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
