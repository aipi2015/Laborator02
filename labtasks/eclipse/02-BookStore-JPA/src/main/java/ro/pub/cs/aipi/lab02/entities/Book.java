package ro.pub.cs.aipi.lab02.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.book")
public class Book extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "title")
	protected String title;
	@Column(name = "subtitle")
	protected String subtitle;
	@ManyToMany(targetEntity = Writer.class)
	@JoinTable(name = "bookstore.author", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "writer_id", referencedColumnName = "id") )
	protected Set<Writer> writers;
	@Column(name = "description")
	protected String description;
	@Column(name = "edition")
	protected Long edition;
	@Column(name = "printing_year")
	protected Long printingYear;
	@OneToOne
	@JoinColumn(name = "collection_id", unique = false, nullable = false, updatable = true)
	protected Collection collection;
	@ManyToMany(targetEntity = Category.class)
	@JoinTable(name = "bookstore.category_content", joinColumns = {
			@JoinColumn(name = "book_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
	protected Set<Category> categories;

	public Book() {
	}

	public Book(String title, String subtitle, Set<Writer> writers, String description, Long edition, Long printingYear,
			Collection collection, Set<Category> categories) {
		this.title = title;
		this.subtitle = subtitle;
		this.writers = writers;
		this.description = description;
		this.edition = edition;
		this.printingYear = printingYear;
		this.collection = collection;
		this.categories = categories;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Set<Writer> getWriters() {
		return writers;
	}

	public void setWriters(Set<Writer> writers) {
		this.writers = writers;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getEdition() {
		return edition;
	}

	public void setEdition(Long edition) {
		this.edition = edition;
	}

	public Long getPrintingYear() {
		return printingYear;
	}

	public void setPrintingYear(Long printingYear) {
		this.printingYear = printingYear;
	}

	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Book book = (Book) object;
		if ((this.id.equals(book.getId())) && (this.title.equals(book.getTitle()))
				&& (this.subtitle.equals(book.getSubtitle())) && (this.writers.equals(book.getWriters()))
				&& (this.description.equals(book.getDescription())) && (this.edition.equals(book.getEdition()))
				&& (this.printingYear.equals(book.getPrintingYear())) && (this.collection.equals(book.getCollection()))
				&& (this.categories.equals(book.getCategories()))) {
			return true;
		}
		return false;
	}
}