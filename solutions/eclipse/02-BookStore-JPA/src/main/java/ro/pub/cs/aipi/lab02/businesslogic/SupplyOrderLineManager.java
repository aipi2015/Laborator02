package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.SupplyOrderLine;

public class SupplyOrderLineManager extends PersistentEntityManager<SupplyOrderLine> {

	public SupplyOrderLineManager() {
		super(SupplyOrderLine.class);
	}

}
