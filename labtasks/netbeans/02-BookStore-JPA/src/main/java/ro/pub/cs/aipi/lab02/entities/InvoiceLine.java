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
@Table(name = "bookstore.invoice_line")
public class InvoiceLine extends PersistentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    @OneToOne
    @JoinColumn(name = "book_presentation_id", unique = false, nullable = false, updatable = true)
    protected BookPresentation bookPresentation;
    @Column(name = "quantity")
    protected Long quantity;

    public InvoiceLine() {
    }

    public InvoiceLine(BookPresentation bookPresentation, Long quantity) {
        this.bookPresentation = bookPresentation;
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }
        InvoiceLine invoiceLine = (InvoiceLine) object;
        if ((this.id.equals(invoiceLine.getId())) && (this.bookPresentation.equals(invoiceLine.getBookPresentation())
                && (this.quantity.equals(invoiceLine.getQuantity())))) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (bookPresentation.toString() + quantity.toString()).hashCode();
    }
}
