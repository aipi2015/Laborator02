package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Writer.class)
public class Writer_ {
	  public static volatile SingularAttribute<Writer, Long> id;
	  public static volatile SingularAttribute<Writer, String> firstName;
	  public static volatile SingularAttribute<Writer, String> lastName;
	  public static volatile SingularAttribute<Writer, String> biography;
}
