package ro.pub.cs.aipi.lab02.entities;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(InvoiceHeader.class)
public class InvoiceHeader_ {
	public static volatile SingularAttribute<InvoiceHeader, Long> id;
	public static volatile SingularAttribute<InvoiceHeader, String> identificationNumber;
	public static volatile SingularAttribute<InvoiceHeader, Date> issueDate;
	public static volatile SingularAttribute<InvoiceHeader, String> state;
	public static volatile SingularAttribute<InvoiceHeader, User> user;
	public static volatile SetAttribute<InvoiceHeader, InvoiceLine> invoiceLines;
}
