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
@Table(name = "bookstore.collection")
public class Collection extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "name")
	protected String name;
	@Column(name = "description")
	protected String description;
	@OneToOne
	@JoinColumn(name="publishing_house_id", unique=false, nullable=false, updatable=true)
	protected PublishingHouse publishingHouse;
	
	public Collection() {
	}
	
	public Collection(String name, String description, PublishingHouse publishingHouse) {
		this.name = name;
		this.description = description;
		this.publishingHouse = publishingHouse;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}
	
	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}
}
