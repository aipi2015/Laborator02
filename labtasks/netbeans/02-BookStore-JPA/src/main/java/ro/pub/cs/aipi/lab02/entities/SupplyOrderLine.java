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
@Table(name = "bookstore.supply_order_line")
public class SupplyOrderLine extends PersistentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @OneToOne
    @JoinColumn(name = "book_presentation_id", unique = false, nullable = false, updatable = true)
    private BookPresentation bookPresentation;
    @Column(name = "quantity")
    protected Long quantity;
    @OneToOne
    @JoinColumn(name = "supply_order_header_id", unique = false, nullable = false, updatable = true)
    private SupplyOrderHeader supplyOrderHeader;

    public SupplyOrderLine() {
    }

    public SupplyOrderLine(BookPresentation bookPresentation, Long quantity, SupplyOrderHeader supplyOrderHeader) {
        this.bookPresentation = bookPresentation;
        this.quantity = quantity;
        this.supplyOrderHeader = supplyOrderHeader;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookPresentation getBookPresentation() {
        return bookPresentation;
    }

    public void setBookPresentation(BookPresentation bookPresentation) {
        this.bookPresentation = bookPresentation;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public SupplyOrderHeader getSupplyOrderHeader() {
        return supplyOrderHeader;
    }

    public void setSupplyOrderHeader(SupplyOrderHeader supplyOrderHeader) {
        this.supplyOrderHeader = supplyOrderHeader;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        SupplyOrderLine supplyOrderLine = (SupplyOrderLine) object;
        if ((this.id.equals(supplyOrderLine.getId()))
                && (this.bookPresentation.equals(supplyOrderLine.getBookPresentation()))
                && (this.quantity.equals(supplyOrderLine.getQuantity()))
                && (this.supplyOrderHeader.equals(supplyOrderLine.getSupplyOrderHeader()))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (bookPresentation.toString() + quantity.toString() + supplyOrderHeader.toString()).hashCode();
    }
}
