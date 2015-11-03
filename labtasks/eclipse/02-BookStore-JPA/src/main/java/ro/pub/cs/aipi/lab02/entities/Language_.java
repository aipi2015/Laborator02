package ro.pub.cs.aipi.lab02.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, Long> id;
	public static volatile SingularAttribute<Language, String> name;
	public static volatile SingularAttribute<Language, String> code;
	public static volatile SingularAttribute<Language, String> description;
}
