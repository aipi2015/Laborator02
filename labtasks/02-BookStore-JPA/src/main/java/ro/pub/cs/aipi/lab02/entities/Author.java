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
@Table(name = "bookstore.author")
public class Author extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@OneToOne
	@JoinColumn(name="book_id", unique=false, nullable=false, updatable=true)
	protected Book book;
	@OneToOne
	@JoinColumn(name="writer_id", unique=false, nullable=false, updatable=true)
	protected Writer writer;
	
	public Author() {
	}
	
	public Author(Long id, Book book, Writer writer) {
		this.id = id;
		this.book = book;
		this.writer = writer;
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
	
	public Writer getWriter() {
		return writer;
	}
	
	public void setWriter(Writer writer) {
		this.writer = writer;
	}

}
