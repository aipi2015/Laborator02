package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.book_presentation")
public class BookPresentation extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "isbn")
	protected Long isbn;
	@OneToOne
	@JoinColumn(name = "book_id", unique = false, nullable = false, updatable = true)
	protected Book book;
	@OneToOne
	@JoinColumn(name = "format_id", unique = false, nullable = false, updatable = true)
	protected Format format;
	@OneToOne
	@JoinColumn(name = "language_id", unique = false, nullable = false, updatable = true)
	protected Language language;
	@Column(name = "price")
	protected Double price;
	@Column(name = "stockpile")
	protected Long stockpile;

	public BookPresentation() {
	}

	public BookPresentation(Long isbn, Book book, Format format, Language language, Double price, Long stockpile) {
		this.isbn = isbn;
		this.book = book;
		this.format = format;
		this.language = language;
		this.price = price;
		this.stockpile = stockpile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIsbn() {
		return isbn;
	}

	public void setIsbn(Long isbn) {
		this.isbn = isbn;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getStockpile() {
		return stockpile;
	}

	public void setStockpile(Long stockpile) {
		this.stockpile = stockpile;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		BookPresentation bookPresentation = (BookPresentation) object;
		if ((this.id.equals(bookPresentation.getId())) && (this.isbn.equals(bookPresentation.getIsbn()))
				&& (this.book.equals(bookPresentation.getBook()))
				&& (this.language.equals(bookPresentation.getLanguage()))
				&& (this.format.equals(bookPresentation.getFormat()))
				&& (this.price.equals(bookPresentation.getPrice()))
				&& (this.stockpile.equals(bookPresentation.getStockpile()))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (isbn.toString() + book.toString() + format.toString() + language.toString() + price.toString()
				+ stockpile.toString()).hashCode();
	}
}
