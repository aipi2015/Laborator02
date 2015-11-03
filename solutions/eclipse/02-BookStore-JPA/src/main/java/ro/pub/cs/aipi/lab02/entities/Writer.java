package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.writer")
public class Writer extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "first_name")
	protected String firstName;
	@Column(name = "last_name")
	protected String lastName;
	@Column(name = "biography")
	protected String biography;

	public Writer() {
	}

	public Writer(String firstName, String lastName, String biography) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.biography = biography;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Writer writer = (Writer) object;
		if ((this.id.equals(writer.getId()))
				&& (this.firstName.equals(writer.getFirstName()) && (this.lastName.equals(writer.getLastName())))) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (firstName + lastName).hashCode();
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
