package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(InvoiceLine.class)
public class InvoiceLine_ {

    public static volatile SingularAttribute<InvoiceLine, Long> id;
    public static volatile SingularAttribute<InvoiceLine, BookPresentation> bookPresentation;
    public static volatile SingularAttribute<InvoiceLine, Long> quantity;
}
