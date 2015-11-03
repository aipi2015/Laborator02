package ro.pub.cs.aipi.lab02.businesslogic;

import ro.pub.cs.aipi.lab02.entities.PublishingHouse;

public class PublishingHouseManager extends PersistentEntityManager<PublishingHouse> {

    public PublishingHouseManager() {
        super(PublishingHouse.class);
    }

}
