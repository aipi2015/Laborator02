package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SupplyOrderLine.class)
public class SupplyOrderLine_ {

    public static volatile SingularAttribute<SupplyOrderLine, Long> id;
    public static volatile SingularAttribute<SupplyOrderLine, BookPresentation> bookPresentation;
    public static volatile SingularAttribute<SupplyOrderLine, Long> quantity;
    public static volatile SingularAttribute<SupplyOrderLine, SupplyOrderHeader> supplyOrderHeader;
}
