package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.format")
public class Format extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "value")
	protected String value;
	@Column(name = "description")
	protected String description;

	public Format() {
	}

	public Format(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!this.getClass().equals(object.getClass())) {
			return false;
		}
		Format format = (Format) object;
		if ((this.id.equals(format.getId()))
				&& (this.value.equals(format.getValue()) && (this.description.equals(format.getDescription())))) {
			return true;
		}
		return false;
	}
}
