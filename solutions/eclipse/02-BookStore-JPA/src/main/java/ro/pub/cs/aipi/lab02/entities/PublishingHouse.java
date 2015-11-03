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
@Table(name = "bookstore.publishing_house")
public class PublishingHouse extends PersistentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "name")
	protected String name;
	@Column(name = "registered_number")
	protected Long registeredNumber;
	@Column(name = "description")
	protected String description;
	@Column(name = "postal_address")
	protected String postalAddress;
	@Column(name = "zip_code")
	protected Long zipCode;
	@OneToOne
	@JoinColumn(name = "country_id", unique = false, nullable = false, updatable = true)
	protected Country country;
	@Column(name = "internet_address")
	protected String internetAddress;

	public PublishingHouse() {
	}

	public PublishingHouse(String name, Long registeredNumber, String description, String postalAddress, Long zipCode,
			Country country, String internetAddress) {
		this.name = name;
		this.registeredNumber = registeredNumber;
		this.description = description;
		this.postalAddress = postalAddress;
		this.zipCode = zipCode;
		this.country = country;
		this.internetAddress = internetAddress;
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

	public Long getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(Long registeredNumber) {
		this.registeredNumber = registeredNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public Long getZipCode() {
		return zipCode;
	}

	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getInternetAddress() {
		return internetAddress;
	}

	public void setInternetAddress(String internetAddress) {
		this.internetAddress = internetAddress;
	}
}