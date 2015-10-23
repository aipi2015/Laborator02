package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Country.class)
public class Country_ {
	  public static volatile SingularAttribute<Country, Long> id;
	  public static volatile SingularAttribute<Country, String> name;
	  public static volatile SingularAttribute<Country, String> code;
	  public static volatile SingularAttribute<Country, String> description;
}
