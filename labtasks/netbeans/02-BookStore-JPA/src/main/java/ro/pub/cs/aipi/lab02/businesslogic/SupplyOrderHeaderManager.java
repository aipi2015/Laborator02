package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.SupplyOrderHeader;

public class SupplyOrderHeaderManager extends PersistentEntityManager<SupplyOrderHeader> {

    public SupplyOrderHeaderManager() {
        super(SupplyOrderHeader.class);
    }

}
