package ro.pub.cs.aipi.lab02.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bookstore.invoice_header")
public class InvoiceHeader extends PersistentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;
	@Column(name = "identification_number")
	protected String identificationNumber;
	@Column(name = "issue_date")
	protected LocalDateTime issueDate;
	@Column(name = "state")
	protected String state;
	@OneToOne
	@JoinColumn(name="user_id", unique=false, nullable=false, updatable=true)
	protected User user;
	@OneToMany
	protected Set<InvoiceLine> invoiceLines;

	public InvoiceHeader() {
	}

	public InvoiceHeader(String identificationNumber, LocalDateTime issueDate, String state, User user,
			Set<InvoiceLine> invoiceLines) {
		this.identificationNumber = identificationNumber;
		this.issueDate = issueDate;
		this.state = state;
		this.user = user;
		this.invoiceLines = invoiceLines;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(Set<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}
}