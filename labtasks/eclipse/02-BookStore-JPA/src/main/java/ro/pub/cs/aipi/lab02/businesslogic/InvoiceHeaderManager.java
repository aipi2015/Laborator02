package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.InvoiceHeader;

public class InvoiceHeaderManager extends PersistentEntityManager<InvoiceHeader> {
	
	public InvoiceHeaderManager() {
		super(InvoiceHeader.class);
	}

}
