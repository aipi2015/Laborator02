package ro.pub.cs.aipi.lab02.entities;

import java.util.Date;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SupplyOrderHeader.class)
public class SupplyOrderHeader_ {

    public static volatile SingularAttribute<SupplyOrderHeader, Long> id;
    public static volatile SingularAttribute<SupplyOrderHeader, String> identificationNumber;
    public static volatile SingularAttribute<SupplyOrderHeader, Date> issueDate;
    public static volatile SingularAttribute<SupplyOrderHeader, String> state;
    public static volatile SingularAttribute<SupplyOrderHeader, PublishingHouse> publishingHouse;
    public static volatile SetAttribute<SupplyOrderHeader, SupplyOrderLine> supplyOrderLines;
}
