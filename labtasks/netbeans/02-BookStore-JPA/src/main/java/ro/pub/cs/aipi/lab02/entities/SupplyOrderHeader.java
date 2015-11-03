package ro.pub.cs.aipi.lab02.entities;

import java.util.Date;
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
@Table(name = "bookstore.supply_order_header")
public class SupplyOrderHeader extends PersistentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @Column(name = "identification_number")
    protected String identificationNumber;
    @Column(name = "issue_date")
    protected Date issueDate;
    @Column(name = "state")
    protected String state;
    @OneToOne
    @JoinColumn(name = "publishing_house_id", unique = false, nullable = false, updatable = true)
    protected PublishingHouse publishingHouse;
    @OneToMany
    protected Set<SupplyOrderLine> supplyOrderLines;

    public SupplyOrderHeader() {
    }

    public SupplyOrderHeader(String identificationNumber, Date issueDate, String state, PublishingHouse publishingHouse,
            Set<SupplyOrderLine> supplyOrderLines) {
        this.identificationNumber = identificationNumber;
        this.issueDate = issueDate;
        this.state = state;
        this.publishingHouse = publishingHouse;
        this.supplyOrderLines = supplyOrderLines;
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Set<SupplyOrderLine> getSupplyOrderLines() {
        return supplyOrderLines;
    }

    public void setSupplyOrderLines(Set<SupplyOrderLine> supplyOrderLines) {
        this.supplyOrderLines = supplyOrderLines;
    }
}
