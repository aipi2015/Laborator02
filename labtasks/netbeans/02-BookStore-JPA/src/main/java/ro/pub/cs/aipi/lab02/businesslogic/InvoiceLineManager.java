package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.InvoiceLine;

public class InvoiceLineManager extends PersistentEntityManager<InvoiceLine> {

    public InvoiceLineManager() {
        super(InvoiceLine.class);
    }

}
