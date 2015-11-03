package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PublishingHouse.class)
public class PublishingHouse_ {
	public static volatile SingularAttribute<PublishingHouse, Long> id;
	public static volatile SingularAttribute<PublishingHouse, String> name;
	public static volatile SingularAttribute<PublishingHouse, Long> registeredNumber;
	public static volatile SingularAttribute<PublishingHouse, String> description;
	public static volatile SingularAttribute<PublishingHouse, String> postalAddress;
	public static volatile SingularAttribute<PublishingHouse, Long> zipCode;
	public static volatile SingularAttribute<PublishingHouse, Country> country;
	public static volatile SingularAttribute<PublishingHouse, String> internetAddress;
}
